package io.andrelucas.app.driver

import io.andrelucas.app.CommandService
import io.andrelucas.app.QueryService
import io.andrelucas.business.driver.CreateAvailableDriver
import io.andrelucas.repository.DriverRepositoryInPhysicalDatabaseImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val driverCommandService = DriverCommandService(
    CreateAvailableDriver(
        DriverRepositoryInPhysicalDatabaseImpl()
    )
)

fun Application.configureDriverRouting() {
    routing {
        createDriver(driverCommandService)
    }
}

fun Route.createDriver(commandService: CommandService<DriverRequest>) {
    post("/driver") {
        val driverRequest = call.receive<DriverRequest>()
        commandService.execute(driverRequest)

        call.response.status(HttpStatusCode.Created)
    }
}
