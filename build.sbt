
scalaVersion := "2.11.9"

resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/releases"

scalacOptions in ThisBuild ++= Seq("-Xexperimental", "-deprecation")

parallelExecution in Test := false

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % Test,
  "org.scalacheck" %% "scalacheck" % "1.12.1" % Test,
  "junit" % "junit" % "4.10" % Test,
  "org.apache.commons" % "commons-math3" % "3.6.1" % Test

)

fork in run := true
