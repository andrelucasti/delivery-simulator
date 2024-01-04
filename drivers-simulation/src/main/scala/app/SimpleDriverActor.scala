package io.andrelucas
package app

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import io.andrelucas.business.driver.{Driver, DriverDTO}

object SimpleDriverActor {

  trait SimpleDriverCommand
  case class Create(driverDTO: DriverDTO, replyTo: ActorRef[Driver]) extends SimpleDriverCommand

  def apply(): Behavior[SimpleDriverCommand] = createDriverBehavior()

  private def createDriverBehavior(): Behavior[SimpleDriverCommand] = Behaviors.receive{ (context, message) =>
    context.log.info(s"Creating Driver By Test $message")

    message match
      case Create(driverDTO, replyTo) =>
        replyTo ! Driver.create(driverDTO.name, driverDTO.modal, driverDTO.deliveryRegion)
    
    Behaviors.same
  }
}
