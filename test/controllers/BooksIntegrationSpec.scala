package controllers

import play.api.libs.json._
import play.api.libs.ws._
import play.api.test._

class BooksIntegrationSpec extends PlaySpecification {

  "Books Controller" should {
    "return all books" in new WithServer {
      val response = await(WS.url("http://localhost:" + port + "/rest/books").get())
      response.status must equalTo(OK)

      response.json.asInstanceOf[JsArray].value.size must equalTo(11)
    }
  }
  "Books Controller" should {
    "return text search result" in new WithServer {
      val response = await(WS.url("http://localhost:" + port + "/rest/search?query=harry").get())
      response.status must equalTo(OK)

      response.json.asInstanceOf[JsArray].value.size must equalTo(1)
    }
  }
}