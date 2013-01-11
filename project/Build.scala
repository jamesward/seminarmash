import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "seminarmash"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    "org.squeryl" %% "squeryl" % "0.9.5-6"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
