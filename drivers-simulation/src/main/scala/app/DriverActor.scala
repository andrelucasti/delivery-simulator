package io.andrelucas
package app

import business.driver.CreateDriver

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object DriverActor {
  def apply(createDriver: CreateDriver):Behavior[DriverCommand] = createDriverBehavior(createDriver)
  
  def createDriverBehavior(createDriver: CreateDriver):Behavior[DriverCommand] = Behaviors.receive{(context, message) =>
    message match
      case CreateDriverCommand(driverDTO) =>
        context.log.info("Creating driver")
        createDriver.execute(driverDTO)
        
        Behaviors.same
  }
}
