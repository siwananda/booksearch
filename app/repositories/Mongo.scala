package repositories

import com.google.inject.{Inject, Singleton}
import models.Book
import org.mongodb.scala.{MongoClient, MongoDatabase}
import play.Logger
import play.api.Configuration
import play.api.inject.ApplicationLifecycle
import play.api.libs.json.Json

import scala.concurrent.Future

@Singleton
class Mongo @Inject()(applicationLifecycle: ApplicationLifecycle, configuration: Configuration) {

  val client: MongoClient = MongoClient()

  private val dbName: String = configuration.getString("mongo.db.name").get

  val db: MongoDatabase = client.getDatabase(dbName)

  applicationLifecycle.addStopHook(() => {
    Logger.warn("Closing Mongo connection")
    Future.successful(client.close())
  })
}
