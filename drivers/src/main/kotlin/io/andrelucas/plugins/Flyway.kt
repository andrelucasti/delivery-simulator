package io.andrelucas.plugins

import io.ktor.server.application.*
import org.flywaydb.core.Flyway


fun Application.flyway(){
    val url = environment.config.property("ktor.db.url").getString()
    val user = environment.config.property("ktor.db.user").getString()
    val password = environment.config.property("ktor.db.password").getString()
    val driverClassName = environment.config.property("ktor.db.driverClassName").getString()
    val location = environment.config.property("ktor.db.migrations").getString()

    val flyway = Flyway.configure()
        .dataSource(url, user, password)
        .driver(driverClassName)
        .locations(location)
        .load()

    flyway.migrate()
}