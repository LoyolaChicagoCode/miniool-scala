name := "miniool-scala"

version := "0.2"

scalaVersion := "3.0.1"

scalacOptions ++= Seq("-unchecked", "-Yexplicit-nulls", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"       % "3.2.9" % Test
)

scalacOptions ++= Seq("-rewrite", "-new-syntax")
