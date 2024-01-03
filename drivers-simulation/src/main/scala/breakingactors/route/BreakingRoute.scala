package io.andrelucas
package breakingactors.route

import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import scala.collection.mutable.{Map => MutableMap}

import java.util.UUID

// NEVER PASS MUTABLE STATES TO OTHER ACTORS
// NEVER PASS THE CONTEXT REFERENCE TO OTHER ACTORS
object BreakingRoute {
  trait RouteCommand
  case class CreateRoute(route: Route) extends RouteCommand
  case class ChangeRouteStatus(routeStatus: RouteStatus) extends RouteCommand
  case class AllocateRoute(driverId: UUID, routeId: UUID) extends RouteCommand
  case class StartDelivery(routeId: UUID) extends RouteCommand
  case class CheckRouteStatus() extends RouteCommand

  trait DriverCommand
  case class CreateDriver(driveDTO: DriveDTO) extends DriverCommand
  case class AttachToRoute(driverId: UUID, routeId: UUID, routes: MutableMap[UUID, Route]) extends DriverCommand
  case class InRoute(routeId: UUID, driverId: UUID) extends DriverCommand

  object RouteDelivery {
    def apply(): Behavior[RouteCommand] = route()

    def route(): Behavior[RouteCommand] = Behaviors.setup{context =>
      val routes: MutableMap[UUID, Route] = MutableMap()

      Behaviors.receiveMessage {
        case CreateRoute(route) =>
          context.log.info(s"Creating a new route ${route.getId}")
          routes.addOne(route.getId, route)

          Behaviors.same
        case AllocateRoute(driverId, routeId) =>
          val driverActor = context.spawn(DriverDelivery(), s"DriverActor-$driverId")
          context.log.info(s"Allocating a new route $routeId")
          val routeOption = routes.get(routeId)

          routeOption.fold(s"[route] route $routeId could not be found")(route => {
            driverActor ! AttachToRoute(driverId, routeId, routes)
          })
          Behaviors.same

        case CheckRouteStatus() =>
          routes.foreach(r => context.log.info(s"Route status ${r._1} is: driverId = ${r._2.getDriverId} status = ${r._2.getStatus}"))
          Behaviors.same
      }
    }
  }

  object DriverDelivery {
    def apply(): Behavior[DriverCommand] = driver()

    // NEVER PASS MUTABLE STATES TO OTHER ACTORS
    // NEVER PASS THE CONTEXT REFERENCE TO OTHER ACTORS
    def driver(): Behavior[DriverCommand] = Behaviors.receive { (context, message) =>
      message match
        case AttachToRoute(driverId, routeId, routes) =>
          context.log.info(s"[driver] allocating the route $routeId to the driver $driverId")
          routes.get(routeId).fold(s"[driver] route $routeId could not be found")(route =>
            routes.remove(routeId)
            val routeAllocated = Route.allocate(driverId, route)
            routes.addOne(routeId, routeAllocated))

          Behaviors.same

    }
  }

  def startSystem(): Unit = {
    val userGuardianBehavior: Behavior[Nothing] = Behaviors.setup { context =>
      val parent = context.spawn(RouteDelivery(), "SystemParent")

      //parent.tell(CreateRoute(Geolocation(0, 0), Geolocation(0, 0)))

      val route: Route = Route.create(Geolocation(0, 0), Geolocation(0, 0))

      parent ! CreateRoute(route)
      parent ! CheckRouteStatus()


      parent ! AllocateRoute(UUID.randomUUID(), route.getId)
      parent ! CheckRouteStatus()

      Behaviors.empty
    }

    val system = ActorSystem(userGuardianBehavior, "MainActorSystem")
    Thread.sleep(1000)
    system.terminate()
  }

  def main(args: Array[String]): Unit = {
    startSystem()
  }
}
