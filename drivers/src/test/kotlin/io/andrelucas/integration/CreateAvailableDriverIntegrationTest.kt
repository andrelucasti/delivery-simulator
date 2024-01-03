package io.andrelucas.integration

import io.andrelucas.business.driver.CreateAvailableDriver
import io.andrelucas.business.driver.Driver
import io.andrelucas.business.region.DeliveryRegion
import io.andrelucas.business.vehicle.VehicleType
import io.andrelucas.repository.DriverRepositoryInMemoryImpl
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.*

class CreateAvailableDriverIntegrationTest : FunSpec({
    test("should create a driver") {
        val driverRepository = DriverRepositoryInMemoryImpl()

        val driver = Driver(UUID.randomUUID(), "André Lucas", VehicleType.CAR, DeliveryRegion.LISBON)
        CreateAvailableDriver(driverRepository).execute(driver)

        driverRepository.findAll().first() shouldBe driver
    }
})
