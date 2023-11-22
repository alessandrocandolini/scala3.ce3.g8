package $package$

import cats.effect.IO
import munit.{CatsEffectSuite, ScalaCheckSuite}

trait DefaultSuite extends CatsEffectSuite with ScalaCheckSuite:

  def assertThrowable[T](t: Throwable)(io: IO[T]): IO[Unit] =
    io.attempt.map(
      assertEquals(_, Left(t))
    )
