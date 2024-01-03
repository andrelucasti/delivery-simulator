package io.andrelucas.app.driver

import io.andrelucas.app.CommandService
import io.andrelucas.business.driver.CreateAvailableDriver
import io.andrelucas.business.driver.Driver

class DriverCommandService(private val createAvailableDriver: CreateAvailableDriver) : CommandService<DriverRequest> {
    override fun execute(command: DriverRequest) {
        val driver = Driver(
            name = command.name,
            vehicleType = command.vehicle,
            deliveryRegion = command.deliveryRegion)

        createAvailableDriver.execute(driver)

    }
}