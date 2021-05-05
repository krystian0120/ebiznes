package models

import play.api.libs.json.{Json, OFormat}

case class Order(orderId: String)

object Order {
  implicit val Order: OFormat[Order] = Json.format[Order]
}
