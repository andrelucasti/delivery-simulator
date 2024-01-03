package io.andrelucas.e2e

import com.google.gson.Gson
import io.andrelucas.app.driver.configureDriverRouting
import io.andrelucas.business.driver.Driver
import io.andrelucas.business.region.DeliveryRegion
import io.andrelucas.business.vehicle.VehicleType
import io.andrelucas.repository.DriverRepositoryInPhysicalDatabaseImpl
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.util.*


class DriverRouteTest : ShouldSpec({
    val driverRepository = DriverRepositoryInPhysicalDatabaseImpl()

    should("return 201 when creating a driver") {
        testApplication {
            application {
                configureDriverRouting()
            }

            val driver = Driver(UUID.randomUUID(), "John Doe", VehicleType.BIKE, DeliveryRegion.LISBON)

            client.post("/driver") {
                contentType(ContentType.Application.Json)
                setBody(Gson().toJson(driver))
            }.apply {
                call.response.status shouldBe HttpStatusCode.Created
            }

            driverRepository.findAll().size shouldBe 1
            driverRepository.findAll().first() shouldBe driver
        }
    }
})