package controllers

import play.api._
import play.api.mvc._
import org.squeryl.PrimitiveTypeMode._
import models.AppDB

object Application extends Controller {
  
  def index = Action { request =>
    request.session.get("loggedIn").map { loggedIn =>
      inTransaction {
        val persons = from(AppDB.personTable)(personTable =>
          select(personTable)
        )
        Ok(views.html.index(persons.seq))
      }
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