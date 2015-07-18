organization := "io.uport"
name := "sbt-template"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"
crossScalaVersions := Seq("2.11.7")

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
  val akkaV       = "2.3.12"
  val akkaStreamV = "1.0"
  val sparkV      = "1.4.1"
  val monocleV    = "1.1.1"
  val kafkaV      = "0.8.2.1"
  val phantomV    = "1.5.0"
  val gatlingV    = "2.1.7"
  val slickV      = "3.0.0"
  val sccV        = "1.4.0-M1"
  val codahaleVersion = "3.0.2"
  Seq(
    "com.typesafe.akka"           %% "akka-actor"                        % akkaV,
    "com.typesafe.akka"           %% "akka-cluster"                      % akkaV,
    "com.typesafe.akka"           %% "akka-remote"                       % akkaV,
    "com.typesafe.akka"           %% "akka-slf4j"                        % akkaV,
    "com.typesafe.akka"           %% "akka-contrib"			 % akkaV,
    "com.typesafe.akka"           %% "akka-stream-experimental"          % akkaStreamV,
    "com.typesafe.akka"           %% "akka-http-experimental"            % akkaStreamV,
    "com.typesafe.akka"           %% "akka-http-spray-json-experimental" % akkaStreamV,
    "com.typesafe.akka"           %% "akka-http-testkit-experimental"    % akkaStreamV,
    "com.typesafe"                %  "config"                            % "1.3.0",
     
    "org.apache.spark"            %% "spark-streaming"                   % sparkV % "provided",
    "org.apache.spark"            %% "spark-mllib"                       % sparkV % "provided",
    "org.apache.spark"            %% "spark-catalyst"                    % sparkV % "provided",
    "org.apache.spark"            %% "spark-sql"                         % sparkV % "provided",
    "org.apache.spark"            %% "spark-graphx"                      % sparkV % "provided",
    "org.apache.spark"            %% "spark-bagel"                       % sparkV % "provided",
    "org.apache.spark"            %% "spark-streaming-kafka"             % sparkV % "provided",

    "org.apache.kafka"            %% "kafka"                             % kafkaV excludeAll(ExclusionRule("org.slf4j"), ExclusionRule("log4j")),

    "com.typesafe.slick"          %% "slick"                             % slickV,
    "com.typesafe.slick"          %% "slick-codegen"                     % slickV,
    "com.github.tminglei"         %% "slick-pg"                          % "0.9.0" excludeAll(ExclusionRule("com.typesafe.slick")),
    "org.reactivestreams"         %  "reactive-streams"                  % "1.0.0",
    "org.postgresql"              %  "postgresql"                        % "9.4-1201-jdbc41",
    
    "com.datastax.spark"          %% "spark-cassandra-connector"         % sccV,
    "com.datastax.spark" 	  %% "spark-cassandra-connector-embedded" % sccV,
    "com.datastax.cassandra" 	  %  "cassandra-driver-core" 	         % "2.1.7.1" excludeAll(ExclusionRule("org.slf4j"), ExclusionRule("log4j")),
    
    "org.reactivemongo"           %% "reactivemongo"                     % "0.11.2",
    "io.scalac"                   %% "reactive-rabbit"                   % "1.0.1" excludeAll(ExclusionRule("org.reactivestreams")),

    "com.github.julien-truffaut"  %% "monocle-core"                      % monocleV,
    "com.github.julien-truffaut"  %% "monocle-macro"                     % monocleV,

    "com.codahale.metrics"        %  "metrics-core"                      % codahaleVersion,
    "com.codahale.metrics" 	  %  "metrics-graphite"                  % codahaleVersion,
     
    "org.slf4j" 		  %  "slf4j-api" 	                 % "1.7.12", 
    "ch.qos.logback"              %  "logback-classic"                   % "1.1.3"  % "compile,runtime,test",
    "org.log4s"                   %% "log4s"                             % "1.1.5",
    "org.scalatest"               %% "scalatest"                         % "2.2.5"  % "test",
    "org.scalacheck"              %% "scalacheck"                        % "1.12.3" % "test",
    "com.typesafe.akka"           %% "akka-testkit"                      % akkaV    % "test",
    "io.gatling.highcharts"       %  "gatling-charts-highcharts"         % gatlingV % "test",
    "io.gatling"                  %  "gatling-test-framework"            % gatlingV % "test"
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
