package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AccessoriesRepository @Inject(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class AccessoriesTable(tag: Tag) extends Table[Accessories](tag, Accessories) {
    def accessoriesId = column[String]("accessoriesId", O.PrimaryKey, O.AutoInc)
    def productMake = column[String]("accessoriesMake")
    def productName = column[String]("accessoriesMake")
    def * = (accessoriesId, productMake, productName) <> ((Accessories.apply _).tupled, Accessories.unapply)
  }

  val accessoriesTable = TableQuery[AccessoriesTable]

  def create(productMake: String, productName: String): Future[AccessoriesTable] = db.run {
    val accessoriesId: String = UUID.randomUUID().toString
    accessoriesTable.insertOrUpdate(Accessories(accessoriesId, productMake, productName))
  }


}
