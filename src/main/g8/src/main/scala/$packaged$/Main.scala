package $package$

import cats.effect.IO
import com.monovore.decline.Opts
import $package$.cli.Args
import $package$.utils.CommandIOAppSimple

object Main extends CommandIOAppSimple(
  name = "$project_name$",
  header = "$project_name$",
  version = "$version$"
):

  override def run: Opts[IO[Unit]] = Args.readArgs.map(program)

  val program: Args => IO[Unit] = args =>
    Cli.program(args) *> Server.program(args)
