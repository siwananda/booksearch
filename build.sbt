
name := "booksearch"

version := "1.0"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

lazy val `booksearch` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

libraryDependencies ++= Seq(jdbc, cache, ws, specs2 % Test)

libraryDependencies ++= Seq(
  "org.mongodb.scala" %% "mongo-scala-driver" % "1.1.1",
  "org.scala-lang.modules" %% "scala-async" % "0.9.5",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test",
  "com.github.simplyscala" %% "scalatest-embedmongo" % "0.2.3-SNAPSHOT",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"
)
