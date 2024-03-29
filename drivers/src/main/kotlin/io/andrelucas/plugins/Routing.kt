package io.andrelucas.plugins

import io.andrelucas.app.driver.configureDriverRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }

    configureDriverRouting()
}
