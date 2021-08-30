# scala3.ce3.g8

A [Giter8][g8] template for sbt-based scala 3 projects using typelevel stack. The template can be helpful for both web servers and CLIs. 

It's configured to setup a number of handful dependencies: 
* [cats-core](https://typelevel.org/cats/) and [cats-effects 3.x](https://typelevel.org/cats-effect/)
* [fs2 3.x](https://fs2.io/) for effectful, resource-safe streams 
* [tapir](https://tapir.softwaremill.com/en/latest/) to describe HTTP endpoints as values and derive implementation of client, server etc; compatible with http4s and sttp, and many others 
* [decline](https://ben.kirw.in/decline/) for command-line argument parsing 
* [scalacheck](https://www.scalacheck.org/) for property-based testing, with [scalacheck-effect](https://github.com/typelevel/scalacheck-effect) to test effectful code 

The stack mirrors a similar stack in Haskell using: conduit, servant, optparse-applicative and quickcheck. 

It also includes 
* [scalafmt](https://scalameta.org/scalafmt/) configuration (via [sbt-scalafmt](https://github.com/scalameta/sbt-scalafmt) sbt plugin)
* [sbt-assembly](https://github.com/sbt/sbt-assembly) plugin to generate "fat" jars (remove it if the goal is to build a library)
* opinionated compiler options (eg, `-Yexplicit-nulls`) 


The generated project is setup to run unit and integration tests (with `sbt test` and `sbt it:test` respectively) 

In the future i would like to add 
* few more code in `Main`
* gthub actions pipeline
* consider using [make-g8](https://github.com/arturopala/make-it-g8)

## Install Giter8

On MAC OS X, Giter8 is available via the Homebrew package manager:
```
brew update
brew install giter8
```

For more options, refer to the original documentation http://www.foundweekends.org/giter8/setup.html

## Usage of this template

To generate a project, once Giter8 is successfully installed on your system, clone this repo and run
```
g8 file:///<local-checkout-dir>/scala3.ce3.g8/ --name=<project name>
```

This will generate a folder `<project name>`, based on the local version of the g8 template.0

For other options see http://www.foundweekends.org/giter8/usage.html#Usage

Template license
----------------
Written in 2021 by Alessandro Candolini alessandro.candolini@gmail.com

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
