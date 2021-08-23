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

  val circe = Seq(
    "io.circe" %% "circe-core"
  ).map(_ % "0.14.1") ++
    Seq("io.circe" %% "circe-fs2" % "0.14.0")

  val fs2 = Seq(
    "fs2-core",
    "fs2-io"
  ).map("co.fs2" %% _ % "3.1.0")

  val cats = Seq(
    "org.typelevel" %% "cats-core" % "2.6.1",
    "org.typelevel" %% "cats-effect" % "3.2.2"
  )

  val db = Seq(
    "org.postgresql" % "postgresql" % "42.2.1",
    "org.tpolecat" %% "skunk-core" % "0.2.0"
  )

  val http = Seq(
    "tapir-core",
    "tapir-sttp-client",
    "tapir-json-circe"
  ).map("com.softwaremill.sttp.tapir" %% _ % "0.19.0-M7")

  val decline = Seq(
    "decline-effect",
    "decline"
  ).map("com.monovore" %% _ % "2.1.0")

  val scalacheckEffect = Seq(
    "scalacheck-effect",
    "scalacheck-effect-munit"
  ).map(
    "org.typelevel" %% _ % "1.0.2"
  )

  val dependencies = circe ++ fs2 ++ cats ++ db ++ http ++ decline

  val testDependencies = (Seq(
    "org.scalacheck" %% "scalacheck" % "1.15.4",
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.3"
  ) ++ scalacheckEffect).map(_ % "it,test")


}
