name := "miniool-scala"

version := "0.2"

scalaVersion := "2.12.7"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  "com.novocode"  %  "junit-interface" % "0.11"  % Test,  // required only for plain JUnit testing
  "org.scalatest" %% "scalatest"       % "3.0.5" % Test
)
