import Dependencies._
import Settings._
import sbtassembly.MergeStrategy

name := """$name$"""
version := "1.0-SNAPSHOT"

lazy val root = project
  .in(file("."))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  .settings(commonSettings)
  .settings(
    name := "$project_name$",
    version := "$version$",
    scalaVersion := "$scala_version$",
    assembly / test := Def
      .sequential(Test / test, IntegrationTest / test)
      .value,
    assembly / assemblyMergeStrategy := customMergeStrategy,
    assembly / assemblyJarName := "$jar_filename$",
    scalafmtOnCompile := true,
    libraryDependencies ++= (dependencies ++ testDependencies)
  )


val customMergeStrategy: String => MergeStrategy = {
  case r if r.endsWith(".conf")            => MergeStrategy.concat
  // https://stackoverflow.com/questions/46287789/running-an-uber-jar-from-sbt-assembly-results-in-error-could-not-find-or-load-m
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _                                   => MergeStrategy.first
}

IntegrationTest / parallelExecution := false
