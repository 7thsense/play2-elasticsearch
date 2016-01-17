name := "play2-elasticsearch"

version := "1.5-SNAPSHOT"

organization := "com.clever-age"

val scala211Version="2.11.7"

scalaVersion in ThisBuild := scala211Version

crossScalaVersions := Seq(scala211Version)

lazy val module = project.in(file("./module"))
	.enablePlugins(PlayScala, PlayJava)

lazy val samples = project.in(file("./samples"))
	.settings(
		publish := {},
		publishLocal := {}
	)

