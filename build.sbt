name := "logback-journal"

organization := "org.gnieh"

version := "0.3.0"

licenses += ("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

homepage := Some(url("https://github.com/gnieh/logback-journal"))

retrieveManaged := true

javaOptions += "-Djna.nosys=true"

libraryDependencies += "net.java.dev.jna" % "jna" % "4.5.2"

libraryDependencies += "ch.qos.logback"  % "logback-classic" % "1.2.3"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

// OSGi settings
enablePlugins(SbtOsgi)
osgiSettings

resourceDirectories in Compile := List()

OsgiKeys.exportPackage := Seq("org.gnieh.logback")

OsgiKeys.additionalHeaders := Map (
  "Bundle-Name" -> "systemd journal appender for logback"
)

OsgiKeys.bundleSymbolicName := "org.gnieh.logback.journal"

OsgiKeys.privatePackage := Seq()

// publish settings
publishMavenStyle := true

publishArtifact in Test := false

// do not happen the scala version
crossPaths := false

// exclude scala library, this is a pure java project
autoScalaLibrary := false

// The Nexus repo we're publishing to.
publishTo := (version { (v: String) =>
    val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
  }).value

pomIncludeRepository := { x => false }

pomExtra :=
  <scm>
    <url>https://github.com/gnieh/logback-journal</url>
    <connection>scm:git:git://github.com/gnieh/logback-journal.git</connection>
    <developerConnection>scm:git:git@github.com:gnieh/logback-journal.git</developerConnection>
    <tag>HEAD</tag>
  </scm>
  <developers>
    <developer>
      <id>satabin</id>
      <name>Lucas Satabin</name>
      <email>lucas.satabin@gnieh.org</email>
    </developer>
  </developers>
  <issueManagement>
    <system>github</system>
    <url>https://github.com/gnieh/logback-journal/issues</url>
  </issueManagement>

