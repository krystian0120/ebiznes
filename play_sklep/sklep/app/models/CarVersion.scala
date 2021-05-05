package models

import play.api.libs.json.{Json, OFormat}

case class CarVersion(carVersionId: String, carMakeId: String, carModelId: String, name: String)

object CarVersion {
  implicit val carVersionFormat: OFormat[CarVersion] = Json.format[CarVersion]
}
