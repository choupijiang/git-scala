name := "git-scala"

version := "0.1"

scalaVersion := "2.13.10"
//
//resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

libraryDependencies += "org.eclipse.jgit" % "org.eclipse.jgit" % "6.4.0.202211300538-r"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.1"
libraryDependencies += "org.apache.tika" % "tika-core" % "2.7.0"
libraryDependencies += "org.apache.tika" % "tika-parsers-standard-package" % "2.7.0"
libraryDependencies += "org.slf4j" % "slf4j-api" % "2.0.6"
// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.20.0"



