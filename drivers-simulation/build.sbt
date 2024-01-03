import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

val akkaVersion = "2.8.0"
val scalaTestVersion = "3.2.15"
val logbackVersion = "1.4.7"

lazy val root = (project in file("."))
  .settings(
    name := "drivers-simulation",
    idePackagePrefix := Some("io.andrelucas")
  )


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
)