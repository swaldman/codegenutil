ThisBuild / resolvers ++= Resolver.sonatypeOssRepos("releases")
ThisBuild / resolvers ++= Resolver.sonatypeOssRepos("snapshots")

ThisBuild / publishTo := {
    if (isSnapshot.value) Some(Resolver.sonatypeRepo("snapshots"))
    else Some(Resolver.url("sonatype-staging", url("https://oss.sonatype.org/service/local/staging/deploy/maven2")))
}

ThisBuild / organization := "com.mchange"
ThisBuild / version      := "0.0.2-SNAPSHOT"

val ScalaVersion = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name                := "codegenutil",
    scalaVersion        := ScalaVersion,
    scalacOptions       += "-explain",
    resolvers           += Resolver.mavenLocal,
    pomExtra            := pomExtraForProjectName_Apache2( name.value )
  )

def pomExtraForProjectName_Apache2( projectName : String ) = {
    <url>https://github.com/swaldman/{projectName}</url>
      <licenses>
          <license>
              <name>The Apache Software License, Version 2.0</name>
              <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
              <distribution>repo</distribution>
          </license>
      </licenses>
      <scm>
          <url>https://github.com/swaldman/{projectName}</url>
          <connection>scm:git:git@github.com:swaldman/{projectName}.git</connection>
      </scm>
      <developers>
          <developer>
              <id>swaldman</id>
              <name>Steve Waldman</name>
              <email>swaldman@mchange.com</email>
          </developer>
      </developers>
}



