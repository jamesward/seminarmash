package controllers

import play.api.mvc._
import utils.FormUtil

object SecurityController extends Controller{

  def showLogin = Action {
    Ok(views.html.login())
  }

  def doLogin = Action { implicit request =>
    
    val maybeSession = FormUtil.formParamHeadOption("password").map { password =>
      request.session + ("password" -> password)
    }
    
    Redirect(routes.PersonController.index()).withSession(maybeSession.getOrElse(request.session))
  }
  
  def isAuthenticated(action: Action[AnyContent]): Action[(Action[AnyContent], AnyContent)] = {
    Security.Authenticated(
      requestHeader => requestHeader.session.get("password").filter(_.equals("codemashrocks")),
      _ => Redirect(routes.SecurityController.showLogin())
    ) { user =>
      Action { request =>
        action(request)
      }
    }
  }
  
}