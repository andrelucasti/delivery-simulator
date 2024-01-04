package io.andrelucas
package app

import akka.actor.testkit.typed.scaladsl.{BehaviorTestKit, ScalaTestWithActorTestKit}
import io.andrelucas.business.driver.{CreateDriver, DriverDTO, DriverRepository}
import io.andrelucas.repository.DriverRepositoryInMemory
import org.scalatest.flatspec.AnyFlatSpecLike

class DriverActorTest extends ScalaTestWithActorTestKit with AnyFlatSpecLike {


  it should "return a driver when it is created" in {
    val driverRepository: DriverRepository = DriverRepositoryInMemory
    val createDriver = CreateDriver(driverRepository)

    //val driverActorRef = testKit.spawn(DriverActor(createDriver), "DriverActorTest")
    val master = BehaviorTestKit(DriverActor(createDriver))

    val driverDTO = DriverDTO("Andre", "Truck", "Germany")
    //driverActorRef ! CreateDriverCommand(driverDTO)
    master.run(CreateDriverCommand(driverDTO))


    val drivers = driverRepository.findAll()

    val driversName = drivers.map(_.name)
    val driversModal = drivers.map(_.modal)
    val driversDeliveryRegion = drivers.map(_.deliveryRegion)

    assert(driversName.contains(driverDTO.name))
    assert(driversModal.contains(driverDTO.modal))
    assert(driversDeliveryRegion.contains(driverDTO.deliveryRegion))

  }
}
