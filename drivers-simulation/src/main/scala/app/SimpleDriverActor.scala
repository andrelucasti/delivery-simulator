package io.andrelucas
package app

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import io.andrelucas.business.driver.{Driver, DriverDTO}

object SimpleDriverActor {

  trait DriverCommand
  case class Create(driverDTO: DriverDTO, replyTo: ActorRef[Driver]) extends DriverCommand

  def apply(): Behavior[DriverCommand] = createDriverBehavior()

  private def createDriverBehavior(): Behavior[DriverCommand] = Behaviors.receive{ (context, message) =>
    context.log.info(s"Creating Driver By Test $message")

    message match
      case Create(driverDTO, replyTo) =>
        replyTo ! Driver.create(driverDTO.name, driverDTO.modal, driverDTO.deliveryRegion)
    
    Behaviors.same
  }
}
