val ScalaVersion = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    organization        := "com.mchange",
    name                := "codegenutil",
    version             := "0.0.1-SNAPSHOT",
    scalaVersion        := ScalaVersion,
    scalacOptions       += "-explain",
    resolvers           += Resolver.mavenLocal,
  )

