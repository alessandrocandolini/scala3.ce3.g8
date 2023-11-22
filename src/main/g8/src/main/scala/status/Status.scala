package status
import cats.effect.IO
import sttp.tapir.json.circe.jsonBody
import io.circe.Codec
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.{PublicEndpoint, Schema, endpoint, infallibleEndpoint, path, statusCode, stringToPath}

import scala.language.unsafeNulls

case class StatusResponse(status: String) derives CanEqual, Codec.AsObject, Schema

object StatusEndpoint:
  val ok: StatusResponse = StatusResponse("ok")

  val endpoint: PublicEndpoint[Unit, Nothing, StatusResponse, Any] =
    infallibleEndpoint.get
      .in("status")
      .out(jsonBody[StatusResponse])

  val handler: Unit => IO[Either[Nothing, StatusResponse]] = _ => IO.pure(Right(ok))

  val fullEndpoint = endpoint.serverLogic(handler)
