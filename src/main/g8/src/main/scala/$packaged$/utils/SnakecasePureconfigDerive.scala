package $package$.utils

import pureconfig.generic.derivation.{
  ConfigReaderDerivation,
  CoproductConfigReaderDerivation,
  ProductConfigReaderDerivation
}
import pureconfig.*

object SnakecasePureconfigDerive
    extends ConfigReaderDerivation
    with CoproductConfigReaderDerivation(ConfigFieldMapping(PascalCase, KebabCase), "type")
    with ProductConfigReaderDerivation(ConfigFieldMapping(CamelCase, SnakeCase))
