package io.andrelucas.business.driver

interface DriverRepository {
    fun save(driver: Driver)
    fun findAll(): List<Driver>
}