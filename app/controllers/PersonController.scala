package controllers

import play.api.mvc.{Action, Controller}
import models.Person

object PersonController extends Controller {
  
  var persons = Vector.empty[Person]
  
  def addToSeminar = Action { request =>
    val person: Person = request.getQueryString("name") match {
      case Some(name) => Person(name)
      case None => Person("")
    }
    
    persons = persons :+ person
    
    println(persons)
    
    Redirect(routes.Application.index())
  } 

}
