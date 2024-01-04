package io.andrelucas
package app

import app.SimpleDriverActor.Create

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import io.andrelucas.business.driver.{Driver, DriverDTO}
import org.scalatest.flatspec.AnyFlatSpecLike

class SimpleDriverActorTest extends ScalaTestWithActorTestKit with AnyFlatSpecLike {

  it should "return a driver when it is created" in {
    val driverActorRef = testKit.spawn(SimpleDriverActor(), "DriverActorTest")
    val probe = testKit.createTestProbe[Driver]("DriverActorTestProbe")

    val driverDTO = DriverDTO("Andre", "Truck", "Germany")
    driverActorRef ! Create(driverDTO, probe.ref)

    val receivedDriver = probe.expectMessageType[Driver]

    assert(receivedDriver.name == driverDTO.name)
    assert(receivedDriver.modal == driverDTO.modal)
    assert(receivedDriver.deliveryRegion == driverDTO.deliveryRegion)
  }
}