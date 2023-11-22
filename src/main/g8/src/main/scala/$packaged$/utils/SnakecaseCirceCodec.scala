package $package$.utils

import io.circe.{Codec, HCursor, Decoder, Json}
import io.circe.derivation.{Configuration, ConfiguredCodec}

import scala.deriving.Mirror

trait SnakecaseCirceCodec[A] extends Codec[A]

object SnakecaseCirceCodec:

  inline final def derived[A](using
    inline mirror: Mirror.Of[A]
  ): SnakecaseCirceCodec[A] =
    new SnakecaseCirceCodec[A] {

      private val derived: ConfiguredCodec[A] = {
        import scala.language.unsafeNulls
        given Configuration = Configuration.default.withSnakeCaseMemberNames
        ConfiguredCodec.derived
      }

      def apply(c: HCursor): Decoder.Result[A] =
        derived.apply(c)

      override def apply(a: A): Json = derived.apply(a).dropNullValues // drop null values by default, avoid deepdrop recursively
    }
