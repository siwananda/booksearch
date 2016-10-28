package repositories

import com.mongodb.client.model.WriteModel
import org.bson.conversions.Bson
import org.mongodb.scala._
import org.mongodb.scala.bson.{BsonObjectId, BsonString}
import org.mongodb.scala.model.{Filters, InsertOneModel}
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{Format, Json}

import scala.concurrent.Future

/**
  * Base repository that give common functionality for other repositories
  *
  * @tparam T Type of models this repository will be serving
  */
trait BaseRepository[T] {

  /** Mongo instance to use. Should be set by implementing repositories */
  def mongo: Mongo

  /** Collection name for the implementing repository */
  def collection: String

  /**
    * Converts [[Document]] to [[T]] by replacing BsonObjectId with String Id
    *
    * @param document the document to convert
    * @param format   implicit formatter
    * @return [[T]] after conversion
    */
  def convertToObject(document: Document)(implicit format: Format[T]): T = {
//    Logger.debug(s"converting Document $document to object")
    Json.parse(document.toJson()).as[T]
  }

  /**
    * Gets all available document for a given collection
    *
    * @param format implicit formatter
    * @return Future seq of T
    */
  def find()(implicit format: Format[T]): Future[Seq[T]] = {
    mongo.db.getCollection(collection).find().map(convertToObject).toFuture()
  }

  /**
    * Gets all document matching the passed in filters
    *
    * @param filters Bson object that represents the filters
    * @param format  implicit formatter
    * @return Future seq of T
    */
  def find(filters: Bson)(implicit format: Format[T]): Future[Seq[T]] = {
    mongo.db.getCollection(collection).find(filters).map(convertToObject).toFuture()
  }

  /**
    * Finds a particular Document given a valid Id
    *
    * @param id     [[BsonObjectId]] in string
    * @param format implicit formatter
    * @return Future of T
    */
  def findById(id: String)(implicit format: Format[T]): Future[T] = {
    find(Filters.eq("_id", BsonObjectId(id))).map(_.head)
  }

  /**
    * Searches current collection on the text indexes based on the given search string
    *
    * @param searchString phrase to search for
    * @param format       implicit formatter
    * @return Future of seq T
    */
  def searchText(searchString: String)(implicit format: Format[T]): Future[Seq[T]] = {
    find(Filters.or(Filters.eq("title", "/^"+searchString+"/"), Filters.text(searchString)))
  }

  /**
    * Bulk Write items into the collection. Purposely left to return observable as the return type won't be
    * convertible to this object's T
    *
    * @param items  List of items to write into the DB
    * @param format implicit formatter
    * @return cold [[Observable]] object, ready for subscription
    */
  def bulkWrite(items: List[T])(implicit format: Format[T]): Observable[BulkWriteResult] = {
    val writableList: List[WriteModel[_ <: Document]] = items
      .map(item => InsertOneModel(Document(Json.stringify(Json.toJson[T](item)))))
    mongo.db.getCollection(collection).bulkWrite(writableList)
  }
}
