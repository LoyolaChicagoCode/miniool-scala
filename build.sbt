name := "miniool-scala"

version := "0.2"

scalaVersion := "3.1.3"

scalacOptions ++= Seq("-unchecked", "-Yexplicit-nulls", "-language:strictEquality", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"       % "3.2.9" % Test
)

scalacOptions ++= Seq("-rewrite", "-new-syntax")
