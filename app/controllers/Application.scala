package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action { request =>
    request.session.get("loggedIn").map { loggedIn =>
      Ok(views.html.index(PersonController.persons))
    }.getOrElse {
      Redirect(routes.Application.showLogin())
    }
  }
  
  def showLogin = Action {
    Ok(views.html.login())
  }
  
  def doLogin = Action { request =>
    request.getQueryString("password") match {
      case Some("codemashrocks") => Redirect(routes.Application.index()).withSession(
        "loggedIn" -> "true"
      )
      case _ => Redirect(routes.Application.showLogin())
    }
  }
  
}