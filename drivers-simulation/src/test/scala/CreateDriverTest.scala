package io.andrelucas

import business.driver.{CreateDriver, DriverDTO}
import repository.DriverRepositoryInMemory

import org.scalatest.flatspec.AnyFlatSpec

class CreateDriverTest extends AnyFlatSpec {
  it should "create a new driver" in {
    val repository = DriverRepositoryInMemory
    val createDriver = CreateDriver(repository)
    DriverDTO("Andre", "Moto", "Recife")
    createDriver.execute(DriverDTO("Andre", "Moto", "Recife"))

    assert(repository.findAll().nonEmpty)

  }
}
