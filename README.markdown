A [Giter8][g8] template for sbt-based scala 3 projects. 

It's configured to setup a number of handful dependencies: 
* [scalacheck](https://www.scalacheck.org/) for property-based testing
* [cats-effects 3.x](https://typelevel.org/cats-effect/)
* [fs2 3.x](https://fs2.io/)

It also includes 
* [scalafmt](https://scalameta.org/scalafmt/) configuration (via [sbt-scalafmt](https://github.com/scalameta/sbt-scalafmt) sbt plugin)
* opinionated compiler options (eg, `-Yexplicit-nulls`) 

In the future i would like to add 
* few more dependencies 
* few more code in `Main`
* gthub actions pipeline 

Template license
----------------
Written in 2021 by Alessandro Candolini alessandro.candolini@gmail.com

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
