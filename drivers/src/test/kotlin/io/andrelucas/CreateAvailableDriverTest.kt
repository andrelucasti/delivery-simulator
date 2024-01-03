package io.andrelucas

import io.andrelucas.business.driver.CreateAvailableDriver
import io.andrelucas.business.driver.Driver
import io.andrelucas.business.driver.DriverRepository
import io.andrelucas.business.driver.DriverStatus
import io.andrelucas.business.region.DeliveryRegion
import io.andrelucas.business.vehicle.VehicleType
import io.kotest.core.spec.style.FunSpec
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class CreateAvailableDriverTest : FunSpec({
    val driverRepository = mockk<DriverRepository>(relaxed = true)

    test("should create a driver") {
        val driver = Driver(UUID.randomUUID(), "André Lucas", VehicleType.CAR, DeliveryRegion.LISBON)
        CreateAvailableDriver(driverRepository).execute(driver)

        verify { driverRepository.save(driver) }
    }

    test("should return a driver status as available when is created") {
        val driver = Driver(UUID.randomUUID(), "André Lucas", VehicleType.CAR, DeliveryRegion.LISBON)
        CreateAvailableDriver(driverRepository).execute(driver)

        verify { driverRepository.save(driver.copy(status = DriverStatus.AVAILABLE)) }
    }
})
