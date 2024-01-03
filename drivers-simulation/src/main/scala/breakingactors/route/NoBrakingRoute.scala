package io.andrelucas

import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import io.andrelucas.breakingactors.route.{Driver, Route}

import java.util.UUID

object NoBrakingRoute {

  trait RouteCommand
  case class CreateRoute(route: Route) extends RouteCommand
  case class Allocate(routeId: UUID, driverId: UUID) extends RouteCommand
  case class CheckRouteStatus(routeId: UUID) extends RouteCommand

  object RouteDelivery {
    def apply(): Behavior[RouteCommand] = routeBehavior()

    // NEVER PASS MUTABLE STATES TO OTHER ACTORS
    // NEVER PASS THE CONTEXT REFERENCE TO OTHER ACTORS
    def routeBehavior(routes: Map[UUID, Route] = Map()): Behavior[RouteCommand] = Behaviors.receive { (context, message) =>
      message match
        case CreateRoute(route) =>
          context.log.info(s"[route] Creating route ${route.getId}")
          routeBehavior(routes + (route.getId -> route))

        case Allocate(routeId, driverId) =>
          context.log.info(s"[route] Allocating a new route $routeId")
          val driverActorRef = context.spawn(DriverDelivery(), driverId.toString)
          val routeOption = routes.get(routeId)
          routeOption.fold(s"[route] route $routeId could not be found")(r => driverActorRef ! AttachToRoute(driverId, routeId))

          routeBehavior(routes)
    }
  }

  trait DriverCommand

  case class CreateDriver(driver: Driver) extends DriverCommand
  case class AttachToRoute(driverId: UUID, routeId: UUID) extends DriverCommand
  case class InRouteCommand(driverId: UUID, routeId: UUID) extends DriverCommand
  case class CheckDriverStatus(driverId: UUID) extends DriverCommand

  object DriverDelivery {
    def apply(): Behavior[DriverCommand] = driverBehavior()

    def driverBehavior(drivers: Map[UUID, Driver] = Map()): Behavior[DriverCommand] = Behaviors.receive { (context, message) =>
      message match
        case CreateDriver(driver) =>
          context.log.info(s"[driver] creating driver ${driver.getId}")
          driverBehavior(drivers + (driver.getId -> driver))

        case AttachToRoute(driverId, routeId) =>
          context.log.info(s"[driver] allocating the route $routeId to the driver $driverId")
          val driverActorRef: ActorRef[DriverCommand] = context.self.narrow
          driverActorRef ! InRouteCommand(driverId, routeId) // =>  context.self.narrow ! InRouteCommand(driverId, routeId)

          driverBehavior(drivers)

        case InRouteCommand(driverId, routeId) =>
          context.log.info(s"[driver] starting the route $routeId to the driver $driverId")

          val driverOption = drivers.get(driverId)
          driverOption.fold(s"[driver] driver $driverId could not be found")(d => drivers + (driverId -> Driver.inRoute(d)))

          driverBehavior(drivers)

        case CheckDriverStatus(driverId) =>
          context.log.info(s"[driver] checking driver $driverId")
          val driverOption = drivers.get(driverId)
          driverOption.fold(s"[driver] driver $driverId could not be found")(d =>
            context.log.info(s"[driver] Driver found and checked = driverId: ${d.getId}, status: ${d.getStatus}"))


          Behaviors.same
    }
  }



  def startSystem(): Unit = {
    val userGuardianBehavior: Behavior[Nothing] = Behaviors.setup { context =>
      val routeParent = context.spawn(RouteDelivery(), "RouteSystemParent")
      val driverParent = context.spawn(DriverDelivery(), "DriverSystemParent")

      val andreDriver = Driver.create("Andre", "Bike", "Recife")
      driverParent ! CreateDriver(andreDriver)

      val route: Route = Route.create(Geolocation(0, 0), Geolocation(0, 0))
      routeParent ! CreateRoute(route)

      routeParent ! Allocate(route.getId, andreDriver.getId)

      Thread.sleep(2000)
      driverParent ! CheckDriverStatus(andreDriver.getId)


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
