ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.3"

lazy val root = (project in file("."))
  .settings(
    name := "hackernews"
  )
libraryDependencies += "com.lihaoyi" %% "upickle" % "2.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
