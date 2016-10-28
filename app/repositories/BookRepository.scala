package repositories

import com.google.inject.{Inject, Singleton}
import com.mongodb.client.model.IndexModel
import com.mongodb.client.model.Indexes._
import models.Book
import org.mongodb.scala._
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.IndexModel
import play.Logger
import play.api.Configuration

@Singleton
class BookRepository @Inject()(config: Configuration, mongoInstance: Mongo) extends BaseRepository[Book] {


  override def mongo: Mongo = mongoInstance

  override def collection: String = "books"

  /**
    * Defines indexes for collection served by this Repository
    * createIndex operation is idempotent, so this is fine to run on every startup
    */
  def defineIndexes(): Unit = {
    val textIndex = compoundIndex(text("title"), text("synopsis"), text("author"))
    mongo.db.getCollection(collection).createIndex(textIndex).subscribe(
      (s: String) => Logger.info("Text index created on book collection"),
      (e: Throwable) => Logger.error(e.getMessage),
      () => Logger.info("Finished creating index")
    )
    mongo.db.getCollection(collection).createIndex(BsonDocument("title"->1)).subscribe(
      (s: String) => Logger.info("Title index created on book collection"),
      (e: Throwable) => Logger.error(e.getMessage),
      () => Logger.info("Finished creating index")
    )
  }

  defineIndexes()

}
