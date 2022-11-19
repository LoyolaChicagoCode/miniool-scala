name := "miniool-scala"

version := "0.2"

scalaVersion := "3.2.1"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Yexplicit-nulls", "-Ysafe-init", "-language:strictEquality")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"       % "3.2.14" % Test
)
