import Dependencies._
import Settings._

name := """$name$"""
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).
  settings(
    name                        := "$name$",
    scalaVersion := Versions.scala,
    scalafmtOnCompile := true,
    libraryDependencies ++= dependencies ++ testDependencies
  )
