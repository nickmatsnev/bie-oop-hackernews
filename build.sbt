ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.9"

lazy val root = (project in file("."))
  .settings(
    name := "hackernews"
  )
libraryDependencies += "com.lihaoyi" %% "upickle" % "1.6.8"