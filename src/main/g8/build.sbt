import Dependencies._
import Settings._
import sbtassembly.MergeStrategy

name := "$project_name$"
version := "$version$"
ThisBuild / scalaVersion := Versions.scala
ThisBuild / scalafmtOnCompile := true
ThisBuild / Compile / javaOptions += "-Dconfig.resource=dev.conf"
ThisBuild / Test / javaOptions += "-Dconfig.resource=test.conf"
ThisBuild / fork := true

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .settings(
    assembly / assemblyMergeStrategy := customMergeStrategy,
    assembly / assemblyJarName := "$jar_filename$",
    assembly / assemblyOutputPath    := file(s"./target/\${(assembly/assemblyJarName).value}"),
    libraryDependencies ++= (dependencies ++ testDependencies)
  )

val customMergeStrategy: String => MergeStrategy = {
  case r if r.endsWith(".conf")            => MergeStrategy.concat
  // https://stackoverflow.com/questions/46287789/running-an-uber-jar-from-sbt-assembly-results-in-error-could-not-find-or-load-m
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _                                   => MergeStrategy.first
}

// make everything in test configuration available to it:test configuration.
lazy val it = (project in file("it"))
    .dependsOn(root % "test->test")
