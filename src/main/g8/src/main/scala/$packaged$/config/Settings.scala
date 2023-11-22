package $package$.config

import cats.{Applicative, MonadError}
import cats.effect.Sync
import cats.implicits.toFlatMapOps
import pureconfig.{ConfigSource, ConfigReader}
import pureconfig.error.ConfigReaderFailures
import $package$.utils.SnakecasePureconfigDerive.derived

case class Settings(
  port: Int,
  host: String,
) derives CanEqual, ConfigReader

object Settings:

  def readDefaultConfig: Either[ConfigReaderFailures, Settings] = ConfigSource.default.load[Settings]

  def raiseError[F[_]](using m : MonadError[F, Throwable]): Either[ConfigReaderFailures, Settings] => F[Settings] = {
    case Left(l)  => m.raiseError(ErrorParsingConfig(l.prettyPrint()))
    case Right(c) => m.pure(c)
  }

  def readConfigOrThrow[F[_]: Sync: Applicative]: F[Settings] =
    Sync[F]
      .delay(readDefaultConfig)
      .flatMap(raiseError)

final case class ErrorParsingConfig(s: String) extends Throwable
