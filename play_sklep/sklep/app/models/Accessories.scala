package models

import play.api.libs.json.{Json, OFormat}

case class Accessories(accessoryId: String, productMake: String, productName: String)

object Accessories {
  implicit val accessoriesFormat: OFormat[Accessories] = Json.format[Accessories]
}
