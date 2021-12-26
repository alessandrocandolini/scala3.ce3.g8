import cats.{Functor, Show}
import cats.effect.*
import cli.Args
import fs2.*
import utils.*
import utils.simpleConsole

object Cli:

  def source : Stream[Pure, String] = Stream("hello", "world")

  def pipeline[F[_] : SimpleConsole : Functor, A : Show] : Stream[F,A] => Stream[F,A] =
    _.evalTap(SimpleConsole[F].println)

  val program: Args => IO[Unit] = _ =>
    source.covary[IO]
      .through(pipeline)
      .compile
      .drain
