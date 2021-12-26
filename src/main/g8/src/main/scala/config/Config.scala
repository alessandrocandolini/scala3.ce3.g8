package config

import cats.Applicative
import cats.effect.Sync
import org.http4s.server.{ServerBuilder, defaults}
import pureconfig.*
import pureconfig.error.ConfigReaderFailures
import cats.implicits.*

case class Config(
  port: Int,
  host: String,
) derives CanEqual

object Config:

  given ConfigReader[Config] =
    ConfigReader.forProduct2("port", "host")(Config.apply)

  def readDefaultConfig: Either[ConfigReaderFailures, Config] = ConfigSource.default.load[Config]

  def raiseError[F[_]: Sync: Applicative]: Either[ConfigReaderFailures, Config] => F[Config] = {
    case Left(l)  => Sync[F].raiseError[Config](ErrorParsingConfig(l.prettyPrint()))
    case Right(c) => Applicative[F].pure(c)
  }

  def readConfigOrThrow[F[_]: Sync: Applicative]: F[Config] =
    Sync[F]
      .delay(readDefaultConfig)
      .flatMap(raiseError)

final case class ErrorParsingConfig(s: String) extends Throwable
