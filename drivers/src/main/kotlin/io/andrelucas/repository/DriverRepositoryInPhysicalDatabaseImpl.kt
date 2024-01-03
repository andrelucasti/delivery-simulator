package io.andrelucas.repository

import io.andrelucas.business.driver.Driver
import io.andrelucas.business.driver.DriverRepository

class DriverRepositoryInPhysicalDatabaseImpl : DriverRepository {

    override fun save(driver: Driver) {

    }

    override fun findAll(): List<Driver> = emptyList()
}