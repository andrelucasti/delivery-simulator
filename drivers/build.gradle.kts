val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val mockk_version: String by project
val kotest_version: String by project
val kotest_extensions_version: String by project
val exposed_version: String by project
val flyway_version: String by project
val postgres_version: String by project
val prometeus_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.7"
}

group = "io.andrelucas"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-gson-jvm")
    implementation("io.ktor:ktor-server-swagger-jvm")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("io.ktor:ktor-server-call-id-jvm")
    implementation("io.ktor:ktor-server-metrics-micrometer-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-config-yaml:2.3.7")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")

    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("org.flywaydb:flyway-core:$flyway_version")

    implementation("io.micrometer:micrometer-registry-prometheus:$prometeus_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
    testImplementation("io.kotest:kotest-assertions-core:$kotest_version")
    testImplementation("io.kotest:kotest-property:$kotest_version")
    testImplementation("io.kotest:kotest-assertions-json-jvm:$kotest_version")
    testImplementation("io.kotest.extensions:kotest-assertions-ktor:$kotest_extensions_version")
    testImplementation("io.mockk:mockk:$mockk_version")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}