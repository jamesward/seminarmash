package controllers

import play.api.mvc.{Action, Controller}
import models.{AppDB, Person}
import org.squeryl.PrimitiveTypeMode._

object PersonController extends Controller {
  
  def addToSeminar = Action { request =>
    request.getQueryString("name") match {
      case Some(name) => {
        inTransaction(AppDB.personTable insert Person(name))
        Redirect(routes.Application.index())
      }
      case _ => BadRequest("Name not specified")
    }
  }

}
