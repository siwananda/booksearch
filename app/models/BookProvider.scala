package models

import play.api.libs.json._

/**
  * Model of a Book Provider. Id and name can be separated to other collection. For current purpose, this should suffice.
  *
  * @param id   code of the book provider, probably AMZN, GPB
  * @param name name of the book provider, Amazon, Google Play
  * @param url  real URL for this book
  */
case class BookProvider(
                         id: String,
                         name: String,
                         url: String
                       ) {
}

object BookProvider {
  implicit val format = Json.format[BookProvider]
}