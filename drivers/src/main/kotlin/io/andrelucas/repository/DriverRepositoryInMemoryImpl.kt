package io.andrelucas.repository

import io.andrelucas.business.driver.Driver
import io.andrelucas.business.driver.DriverRepository

class DriverRepositoryInMemoryImpl : DriverRepository {
    private val drivers = mutableListOf<Driver>()

    override fun save(driver: Driver) {
        drivers.add(driver)
    }

    override fun findAll(): List<Driver> = drivers
}