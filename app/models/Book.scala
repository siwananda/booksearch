package models

import play.api.libs.json.Json

/**
  * Model of a book
  * @param _id is the book's isbn since this should already be unique
  * @param title title of the book
  * @param synopsis short synopsis for a particular book
  * @param author the author of the book
  * @param providers list of [[BookProvider]] for easy linking to real site
  */
case class Book(_id: String,
                title: String,
                synopsis: String,
                author: String,
                providers: List[BookProvider]
               ) {}

object Book {
  implicit val format = Json.format[Book]
}