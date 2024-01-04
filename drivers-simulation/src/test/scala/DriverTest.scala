package io.andrelucas

import io.andrelucas.business.driver.Driver
import org.scalatest.flatspec.AnyFlatSpec

class DriverTest extends AnyFlatSpec {
  it should "create and return a new driver" in {
    val andreDriver = Driver.create("andre", "foot", "recife")

    assert(andreDriver.name == "andre")
    assert(andreDriver.modal == "foot")
    assert(andreDriver.deliveryRegion == "recife")
  }
}
