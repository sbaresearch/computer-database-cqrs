import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "computer-database-web"
    val appVersion      = "1.0-SNAPSHOT"
	
	val axonVersion = "2.0.3"
	val hibernateVersion = "4.2.3.Final"
	val springVersion = "3.2.3.RELEASE"

    val appDependencies = Seq(
      javaCore,
      javaJdbc,
      javaJpa,
	  "org.sbaresearch" % "computer-database-cqrs" % "1.0-SNAPSHOT",
      "org.axonframework" % "axon" % axonVersion,
	  "org.hibernate" % "hibernate-entitymanager" % hibernateVersion,
      "org.hibernate" % "hibernate-core" % hibernateVersion,
	  "org.springframework" % "spring-context" % springVersion,
      "org.springframework" % "spring-core" % springVersion,
      "org.springframework" % "spring-beans" % springVersion,
      "org.springframework" % "spring-aop" % springVersion,
      "org.springframework" % "spring-tx" % springVersion,
      "org.springframework" % "spring-orm" % springVersion
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      resolvers += "Maven Repository" at "http://mvnrepository.com/artifact/",
	  resolvers += "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository",
      ebeanEnabled := false   
    )
}
