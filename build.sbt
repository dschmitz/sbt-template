organization := "io.uport"
name := "sbt-template"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.5"
crossScalaVersions := Seq("2.11.5")

description := "A purely functional Scala Application"
homepage := Some(url("http://github.com/uport"))

licenses := Seq("The Apache Software License, Version 2.0"
  -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.typesafeRepo("releases")
)

libraryDependencies ++= {
  val akkaV       = "2.3.9"
  val akkaStreamV = "1.0-M3"
  val sparkV      = "1.2.1"
  val monocleV    = "1.0.1"
  val kafkaV      = "0.8.2.0"
  val phantomV    = "1.5.0"
  val gatlingV    = "2.1.4"
  Seq(
    "com.typesafe.akka"           %% "akka-actor"                  % akkaV,
    "com.typesafe.akka"           %% "akka-cluster"                % akkaV,
    "com.typesafe.akka"           %% "akka-remote"                 % akkaV,
    "com.typesafe.akka"           %% "akka-slf4j"                  % akkaV,
    "com.typesafe.akka"           %% "akka-stream-experimental"          % akkaStreamV,
    "com.typesafe.akka"           %% "akka-http-core-experimental"       % akkaStreamV,
    "com.typesafe.akka"           %% "akka-http-experimental"            % akkaStreamV,
    "com.typesafe.akka"           %% "akka-http-spray-json-experimental" % akkaStreamV,
    "com.typesafe.akka"           %% "akka-http-testkit-experimental"    % akkaStreamV,
    "com.typesafe"                %  "config"                      % "1.2.1",
     
    "org.apache.spark"            %% "spark-streaming"             % sparkV,
    "org.apache.spark"            %% "spark-mllib"                 % sparkV,
    "org.apache.spark"            %% "spark-catalyst"              % sparkV,
    "org.apache.spark"            %% "spark-sql"                   % sparkV,
    "org.apache.spark"            %% "spark-graphx"                % sparkV,
    "org.apache.spark"            %% "spark-bagel"                 % sparkV,

    "org.apache.kafka"            %% "kafka"                       % kafkaV,

    "com.typesafe.slick"          %% "slick"                       % "3.0.0-M1",
    "org.reactivestreams"         %  "reactive-streams"            % "1.0.0.RC3",
    
    "org.postgresql"              %  "postgresql"                  % "9.4-1200-jdbc41",
    "org.reactivemongo"           %% "reactivemongo"               % "0.11.0-SNAPSHOT",
    "io.scalac"                   %% "reactive-rabbit"             % "0.2.2",

    "com.github.julien-truffaut"  %% "monocle-core"                % monocleV,
    "com.github.julien-truffaut"  %% "monocle-macro"               % monocleV,
    "org.log4s"                   %% "log4s"                       % "1.1.3",
    "org.scalatest"               %% "scalatest"                   % "2.2.4"  % "test",
    "org.specs2"                  %% "specs2"                      % "3.0-M3" % "test",
    "org.scalacheck"              %% "scalacheck"                  % "1.12.2" % "test",
    "ch.qos.logback"              %  "logback-classic"             % "1.1.2"  % "test",
    "com.typesafe.akka"           %% "akka-testkit"                % akkaV    % "test",
    "io.gatling.highcharts"       %  "gatling-charts-highcharts"   % gatlingV % "test",
    "io.gatling"                  %  "gatling-test-framework"      % gatlingV % "test"
  )
}

scalacOptions ++= Seq(
  "-deprecation",
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import"
)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

wartremover.wartremoverSettings

wartremover.wartremoverErrors in (Compile, compile) ++= Seq(
  wartremover.Wart.Any,
  wartremover.Wart.Any2StringAdd,
  wartremover.Wart.EitherProjectionPartial,
  wartremover.Wart.OptionPartial,
  wartremover.Wart.Product,
  wartremover.Wart.Serializable,
  wartremover.Wart.ListOps
)

enablePlugins(GatlingPlugin)
