package io.andrelucas.business.driver

class CreateAvailableDriver(private val driverRepository: DriverRepository) {

    fun execute(driver: Driver) {
        driverRepository.save(driver)
    }
}