package io.andrelucas

import io.andrelucas.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    flyway()
    configureSerialization()
    configureHTTP()
    configureMonitoring()
    configureRouting()
}
