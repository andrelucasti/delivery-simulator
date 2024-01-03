package io.andrelucas

import io.andrelucas.driver.Driver
import org.scalatest.flatspec.AnyFlatSpec

class DriverTest extends AnyFlatSpec {
  it should "create and return a new driver" in {
    val andreDriver = Driver.create("andre", "foot", "recife")

    assert(andreDriver.getName == "andre")
    assert(andreDriver.getModal == "foot")
    assert(andreDriver.getDeliveryRegion == "recife")
  }
}
