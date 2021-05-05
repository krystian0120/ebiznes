package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CarMakeRepository @Inject(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CarMakeTable(tag: Tag) extends Table[CarMake](tag, CarMake) {
    def carMakeId = column[String]("carMakeId", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (carMakeId, name) <> ((CarMake.apply _).tupled, CarMake.unapply)
  }

  val carMakeTable = TableQuery[CarMakeTable]

  def create(name: String): Future[CarMakeTable] = db.run {
    val carMakeId: String = UUID.randomUUID().toString
    carMakeTable.insertOrUpdate(CarMake(carMakeId, name))
  }


}
