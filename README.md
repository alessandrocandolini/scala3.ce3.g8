# scala3.ce3.g8

[![Scala CI](https://github.com/alessandrocandolini/scala3.ce3.g8/actions/workflows/scala.yml/badge.svg)](https://github.com/alessandrocandolini/scala3.ce3.g8/actions/workflows/scala.yml)

Unofficial opinionated [Giter8][g8] template for **sbt-based scala 3** projects making usage of the typelevel stack. The template can be helpful for both web servers and CLIs.

## Run

### Using sbt

Assuming `sbt` is available, generate a new project using
```
sbt new git@github.com:alessandrocandolini/scala3.ce3.g8.git  --name=<project name> --generate_github_actions=true --package=com.alessandrocandolini --is_server=true --force
```

or from a local clone of this repo
```
sbt new file:///<local-checkout-dir>/scala3.ce3.g8/  --name=<project name> --generate_github_actions=true --package=com.alessandrocandolini --is_server=true --force
```

Variables:
* `name`: name of the generated folder and project name
* `generate_github_actions` (true, false): tell g8 whether to also generate github actions (true by default)
* `package`: package name (default: `com.alessandrocandolini`). The template does NOT attempt at appending the project name as last part of the package, so if you want the package to contain also the project name be sure it's included

### Using g8

On MAC OS X, Giter8 is available via the Homebrew package manager:
```
brew update
brew install giter8
```

For more options, refer to the original documentation http://www.foundweekends.org/giter8/setup.html

Once `g8` is installed on the machine (see next section), it is also possible to generate a new project from a local clone of the repo
```
g8 file:///<local-checkout-dir>/scala3.ce3.g8/ --name=<project name>
```

See http://www.foundweekends.org/giter8/usage.html#Usage for more details

## Structure of the generated project

The generated project includes the following **dependencies**:
* [cats-core](https://typelevel.org/cats/) and [cats-effects 3.x](https://typelevel.org/cats-effect/)
* [fs2 3.x](https://fs2.io/) for effectful, resource-safe streams
* [tapir](https://tapir.softwaremill.com/en/latest/) to describe HTTP endpoints as values and derive implementation of client, server etc; compatible with http4s and sttp, and many others
* [decline](https://ben.kirw.in/decline/) for command-line argument parsing
* [pureconfig-core](https://pureconfig.github.io/) to parse `resources/application.conf` configuration
* [skunk](https://github.com/tpolecat/skunk) for postgresql database access
* [scalacheck](https://www.scalacheck.org/) for property-based testing, with [scalacheck-effect](https://github.com/typelevel/scalacheck-effect) to test effectful code

the following **sbt-plugins**:
* [scalafmt](https://scalameta.org/scalafmt/) configuration (via [sbt-scalafmt](https://github.com/scalameta/sbt-scalafmt) sbt plugin)
* [sbt-assembly](https://github.com/sbt/sbt-assembly) plugin to generate "fat" jars (remove it if the goal is to build a library)

and will setup opinionated **compiler options**, eg, `-language:strictEquality"`, `-Yexplicit-nulls`, `-source:future`, etc.

The generated project is setup to run **unit and integration tests** (with `sbt test` and `sbt it:test` respectively)

The generated project contains a simple `.github/workflow/scala.yml` configuration to setup **github actions** to run `sbt assembly`. It's a very basic setup, completely independent from the scala project. If you are not using github, just remove the `.github` folder. If you want a more advanced setup, you might be interested in exploring [sbt-github-actions](https://github.com/djspiewak/sbt-github-actions).


## Todo

* ~the CI is relying on `g8test`, unfortunately this does not easily detect issues like ` An unexpected error occurred while processing the template. Check that all literal '$' are properly escaped with '\$'` due to https://github.com/foundweekends/giter8/issues/334~ DONE
* scaffolding (to conditionally generate either a cli or a server: i often need either one or the other)
* improve github actions pipeline
* consider caching dependencies also in the main github action to test this repo
* consider testing the generated github actions using something like [act](https://github.com/nektos/act) (at least a dry run)
* ~consider using [make-g8](https://github.com/arturopala/make-it-g8)~ i've tried it in another project and didn't really help me that much

## Giter8 crash course


* Giter8 generates a project that has exactly the same structure of the [src/main/g8](src/main/g8) folder
* template variables are defined in the [default.properties](src/main/g8/default.properties) file
* template variables can be accessed as `$name of the variable$` (ie, between `$`) from everywhere
* `$` is the reserved symbol, whenever you need to use a `$` for purposes other than referring to a template variable be sure to escape it as `\$`; this is a typical source of errors

A more comprehensive guide here: http://www.foundweekends.org/giter8/Contents+in+Depth.html

Template license
----------------
Written in 2021 by Alessandro Candolini alessandro.candolini@gmail.com

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
