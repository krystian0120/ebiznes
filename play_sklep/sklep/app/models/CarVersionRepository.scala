package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CarVersionRepository @Inject(dbConfigProvider: DatabaseConfigProvider, carMakeRepository: CarMakeRepository,
  carModelRepository: CarModelRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CarVersionTable(tag: Tag) extends Table[CarVersion](tag, CarVersion) {
    val carMake = TableQuery[carMakeRepository.CarMake]
    val carModel = TableQuery[carModelRepository.CarModel]

    def carVersionId = column[String]("carModelId", O.PrimaryKey, O.AutoInc)
    def carMakeId = foreignKey("carMakeId", carMakeId, carMake)(_.carMake, onUpdate = ForeignKeyAction.NoAction ,
      onDelete = ForeignKeyAction.Cascade)
    def carModelId = foreignKey("carModelId", carModelId, carModel)(_.carModel, onUpdate = ForeignKeyAction.NoAction ,
      onDelete = ForeignKeyAction.Cascade)
    def name = column[String]("name")
    def * = (carVersionId, carMakeId, carModelId, name) <> ((CarVersion.apply _).tupled, CarVersion.unapply)
  }

  val carVersionTable = TableQuery[CarVersionTable]

  def create(name: String, carMakeId: String, carModelId: String): Future[CarVersionTable] = db.run {
    val carVersionId: String = UUID.randomUUID().toString
    carVersionTable.insertOrUpdate(CarVersion(carVersionId, carModelId, carMakeId, name))
  }
}
