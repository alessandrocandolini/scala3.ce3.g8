import cats.effect.{IO, IOApp}
import cats.effect.std.Console
import cats.Show
import cli._
import com.monovore.decline.Opts
import fs2._
import cats.Functor

object Main extends CommandIOAppSimple(
      name = "simple cli",
      header = "simple cli",
      version = "0.1"
    ):

  override def run: Opts[IO[Unit]] = Args.readArgs.map(program)

  def source : Stream[Pure, String] = Stream("hello", "world")

  def pipeline[F[_] : Console : Functor, A : Show] : Stream[F,A] => Stream[F,A] =
    _.evalTap(Console[F].println)

  val program: Args => IO[Unit] = _ =>
    source.covary[IO]
      .through(pipeline)
      .compile
      .drain
