package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class OrderRepository @Inject(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class OrderTable(tag: Tag) extends Table[Order](tag, Order) {
    def orderId = column[String]("orderId", O.PrimaryKey, O.AutoInc)
    def * = (orderId) <> ((Order.apply _).tupled, Order.unapply)
  }

  val orderTable = TableQuery[Order]

  def create(): Future[Order] = db.run {
    val orderId: String = UUID.randomUUID().toString
    orderTable.insertOrUpdate(Order(orderId))
  }
}
