name := "stereotypes-with-scalaz"

scalaVersion := "2.10.4"

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype OSS Public Repositories" at "https://oss.sonatype.org/content/groups/public/",
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "org.scalatest" % "scalatest_2.10" % "2.1.3" % "test",
  "junit" % "junit" % "4.11" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions")

initialCommands in console := "import scalaz._, Scalaz._"
