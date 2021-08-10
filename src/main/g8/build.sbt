import Dependencies._
import Settings._

name := """$name$"""
version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(Defaults.itSettings)
  .settings(
    name := "$project_name$",
    scalaVersion := Versions.scala,
    assembly / assemblyJarName := "$jar_filename$",
    assembly / test := Def.sequential(Test / test, IntegrationTest / test).value,
    scalafmtOnCompile := false,
    libraryDependencies ++= (dependencies ++ testDependencies)
  )
  .configs(IntegrationTest)
