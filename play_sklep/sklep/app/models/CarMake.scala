package models

import play.api.libs.json.{Json, OFormat}

case class CarMake(carMakeId: String, name: String)

object CarMake {
  implicit val carMakeFormat: OFormat[CarMake] = Json.format[CarMake]
}
