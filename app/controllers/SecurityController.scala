package controllers

import play.api.mvc._

object SecurityController extends Controller{

  def showLogin = Action {
    Ok(views.html.login())
  }

  def doLogin = Action { request =>
    Redirect(routes.PersonController.index()).withSession {
      request.getQueryString("password").map { password =>
        request.session + ("password" -> password)
      }.getOrElse(request.session)
    }
  }
  
  def isAuthenticated(action: Action[AnyContent]): Action[(Action[AnyContent], AnyContent)] = {
    Security.Authenticated({ requestHeader: RequestHeader =>
      requestHeader.session.get("password").filter(_.equals("codemashrocks"))
    },
    { requestHeader: RequestHeader =>
      Redirect(routes.SecurityController.showLogin())
    }) { user =>
      Action { request =>
        action(request)
      }
    }
  }
  
}