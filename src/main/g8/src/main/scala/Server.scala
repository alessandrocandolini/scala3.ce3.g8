import cats.effect.*
import cats.implicits.*
import cats.Applicative
import cli.Args
import config.Config
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import status.StatusEndpoint.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.http4s.Http4sServerInterpreter

object Server:

  val statusRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(fullEndpoint)

  val routes = statusRoutes.orNotFound

  val program: Args => IO[Unit] = _ =>
    Config.readConfigOrThrow[IO].flatMap { c =>
      BlazeServerBuilder[IO]
        .bindHttp(c.port, c.host)
        .withHttpApp(routes)
        .serve
        .compile
        .drain
    }
