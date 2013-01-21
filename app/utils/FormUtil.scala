package utils

import play.api.mvc.{AnyContent, Request}


object FormUtil {

  def formParamHeadOption(name: String)(implicit request: Request[AnyContent]): Option[String] = for {
    form <- request.body.asFormUrlEncoded
    valueSeq <- form.get(name)
  } yield valueSeq.head

}
