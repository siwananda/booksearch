package controllers

import com.google.inject.Inject
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc._
import services.BookService

import scala.concurrent.Future

class BookController @Inject()(bookService: BookService) extends Controller {

  def books() = Action.async { implicit request =>
    bookService.findAll() flatMap { books =>
      Future.successful(Ok(Json.toJson(books)))
    } recover {
      case t: Throwable =>
        InternalServerError(t.getMessage)
    }
  }

  def search(query: String) = Action.async { implicit request =>
    bookService.searchText(query) flatMap { books =>
      Future.successful(Ok(Json.toJson(books)))
    } recover {
      case t: Throwable =>
        InternalServerError(t.getMessage)
    }
  }
}
