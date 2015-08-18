//import xerial.sbt.Sonatype.SonatypeKeys._
//import xerial.sbt.Sonatype._

name := "play2-elasticsearch"

version := "1.5-SNAPSHOT"

organization := "com.clever-age"

javacOptions := Seq("-Xlint:deprecation")

libraryDependencies ++= Seq(
  javaCore,
  specs2 % Test,
  // Add your project dependencies here
  "junit" % "junit" % "4.12" % Test,
  "org.easytesting" % "fest-assert" % "1.4" % Test,
  "org.elasticsearch" % "elasticsearch" % "1.7.1",
  "org.codehaus.groovy" % "groovy-all" % "2.3.8",
  "org.apache.commons" % "commons-lang3" % "3.1"
)

// sonatypeSettings


//profileName := "com.clever-age"

//crossPaths := false

//publishMavenStyle := true

//publishArtifact in Test := false

//pomIncludeRepository := { _ => false }
//
//publishTo := {
//  val nexus = "https://oss.sonatype.org/"
//  if (isSnapshot.value)
//    Some("snapshots" at nexus + "content/repositories/snapshots")
//  else
//    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
//}
//
//licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))
//
//homepage := Some(url("https://github.com/cleverage/play2-elasticsearch"))
//
//pomExtra := (
//  <scm>
//    <url>git@github.com:cleverage/play2-elasticsearch.git</url>
//    <connection>scm:git:git@github.com:cleverage/play2-elasticsearch.git</connection>
//  </scm>
//    <developers>
//      <developer>
//        <id>nboire</id>
//        <name>Nicolas Boire</name>
//      </developer>
//      <developer>
//        <id>mguillermin</id>
//        <name>Matthieu Guillermin</name>
//        <url>http://matthieuguillermin.fr</url>
//      </developer>
//    </developers>)

//lazy val module = project.in(file("."))
//  .enablePlugins(PlayJava,PlayScala)
