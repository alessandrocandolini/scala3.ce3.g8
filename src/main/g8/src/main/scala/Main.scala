import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  val run = IO.println("Hello world!")
