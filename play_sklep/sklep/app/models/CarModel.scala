package models

import play.api.libs.json.{Json, OFormat}

case class CarModel(carModelId: String, carMakeId: String, name: String)

object CarModel {
  implicit val carModelFormat: OFormat[CarModel] = Json.format[CarModel]
}
