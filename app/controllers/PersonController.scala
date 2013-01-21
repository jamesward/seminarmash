package controllers

import scala.util.{Try, Success, Failure}
import play.api.mvc.{Action, Controller}
import org.squeryl.PrimitiveTypeMode._

import controllers.SecurityController.isAuthenticated
import models.{AppDB, Person}
import utils.FormUtil

object PersonController extends Controller {

  def index = isAuthenticated {
    Action { request =>
      Try(inTransaction(views.html.index(from(AppDB.personTable)(select(_)).seq))) match {
        case Success(page) => Ok(page)
        case Failure(error) => InternalServerError(error.getMessage)
      }
    }
  }
  
  def addToSeminar = isAuthenticated {
    Action { implicit request =>
      FormUtil.formParamHeadOption("name").filter(_.nonEmpty).map { name =>
        Try {
          inTransaction(AppDB.personTable.insert(Person(name)))
        } match {
          case Success(person) => Redirect(routes.PersonController.index())
          case Failure(error) => InternalServerError(error.getMessage)
        }
      }.getOrElse {
        BadRequest("Name not specified")
      }
    }
  }

}
