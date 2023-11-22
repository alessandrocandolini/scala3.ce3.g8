package $package$.config
import munit.FunSuite
import pureconfig.*

class SettingsSpec extends FunSuite:

  test("fail to parse invalid input") {
    assert(ConfigSource.string("{}").load[Settings].isLeft)
  }

  test("correctly parse valid input") {

    val s: String =
      """
        |port = 1212
        |host = "localhost"
        """.stripMargin

    val expected: Settings = Settings(
      port = 1212,
      host = "localhost"
    )

    val actual = ConfigSource.string(s).load[Settings]
    assertEquals(actual, Right(expected))

  }

  test("can parse actual application.conf successfully") {
    assert(Settings.readDefaultConfig.isRight)
  }
