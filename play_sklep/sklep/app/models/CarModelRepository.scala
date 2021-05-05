package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CarModelRepository @Inject(dbConfigProvider: DatabaseConfigProvider, carMakeRepository:
  CarMakeRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CarModelTable(tag: Tag) extends Table[CarModel](tag, CarModel) {
    val carMake = TableQuery[carMakeRepository.CarMake]

    def carModelId = column[String]("carModelId", O.PrimaryKey, O.AutoInc)
    def carMakeId = foreignKey("carMakeId", carMakeId, carMake)(_.carMake, onUpdate = ForeignKeyAction.NoAction ,
      onDelete = ForeignKeyAction.Cascade)
    def name = column[String]("name")
    def * = (carMakeId, carModelId, name) <> ((CarModel.apply _).tupled, CarModel.unapply)
  }

  val carModelTable = TableQuery[CarModelTable]

  def create(name: String, carMakeId: String): Future[CarModelTable] = db.run {
    val carModelId: String = UUID.randomUUID().toString
    carModelTable.insertOrUpdate(CarModel(carModelId, carMakeId, name))
  }
}
