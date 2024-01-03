package io.andrelucas


import io.andrelucas.business.driver.{CreateDriver, DriverDTO}
import io.andrelucas.repository.DriverRepositoryInMemory
import org.scalatest.flatspec.AnyFlatSpec

class CreateDriverTest extends AnyFlatSpec {
  it should "create a new driver" in {
    val repository = DriverRepositoryInMemory
    val createDriver = CreateDriver(repository)
    DriverDTO("Andre", "Moto", "Recife")
    createDriver.execute(DriverDTO("Andre", "Moto", "Recife"))

    assert(repository.findAll().nonEmpty)

    val driversName = repository.findAll().map(d=> d.getName)
    val driversModal = repository.findAll().map(d => d.getModal)
    val driversDeliveryRegion = repository.findAll().map(d => d.getDeliveryRegion)

    assert(driversName.contains("Andre"))
    assert(driversModal.contains("Moto"))
    assert(driversDeliveryRegion.contains("Recife"))

  }
}
