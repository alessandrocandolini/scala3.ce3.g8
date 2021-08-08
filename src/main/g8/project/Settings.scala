import sbt._
import Keys._

object Settings {

  val commonSettings: Seq[Def.Setting[_]] = Seq(
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-language:strictEquality",
      "-language:postfixOps",
      "-Yexplicit-nulls"
    )
  )

}

object Dependencies {
  val cats = Seq(
    "org.typelevel" %% "cats-core"   % Versions.catsCore,
    "org.typelevel" %% "cats-effect" % Versions.catsEffect
  )

  val fs2          = Seq(
    "co.fs2" %% "fs2-core",
    "co.fs2" %% "fs2-io"
  ).map(_ % Versions.fs2)

  val dependencies = cats ++ fs2

  val testDependencies = Seq(
    "org.scalacheck" %% "scalacheck"          % Versions.scalacheck,
    "org.typelevel"  %% "scalacheck-effect"   % Versions.scalacheckEffectVersion,
  ).map(_ % Test)
}

object Versions {

  val scala                   = "3.0.1"
  val catsCore                = "2.6.1"
  val catsEffect              = "3.2.2"
  val scalacheckEffectVersion = "1.0.2"
  val fs2                     = "3.1.0"
  val scalacheck              = "1.15.4"

}
