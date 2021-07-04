import Dependencies._
import Settings._

name := """$name$"""
version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
     name                        := "$project_name$",
     scalaVersion := Versions.scala,
     assembly / assemblyJarName := "$jar_filename$",
     assembly / test := (Test / test).value,
     scalafmtOnCompile := true,
     libraryDependencies ++= (dependencies ++ testDependencies)
   )
