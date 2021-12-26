package status
import cats.Applicative
import cats.effect.*
import cats.implicits.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import sttp.tapir.server.ServerEndpoint
import utils.MyCodecAsObject

import scala.language.unsafeNulls

case class StatusResponse(status: String) derives CanEqual, MyCodecAsObject

object StatusEndpoint:
  val ok: StatusResponse = StatusResponse("ok")

  val endpoint: PublicEndpoint[Unit, Nothing, StatusResponse, Any] =
    infallibleEndpoint.get
      .in("status")
      .out(jsonBody[StatusResponse])

  val handler: Unit => IO[Either[Nothing, StatusResponse]] = _ => IO.pure(Right(ok))

  val fullEndpoint = endpoint.serverLogic(handler)
