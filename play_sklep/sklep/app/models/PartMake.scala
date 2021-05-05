package models

import play.api.libs.json.{Json, OFormat}

case class PartMake(parMakeId: String, name: String)

object PartMake {
  implicit val partMakeFormat: OFormat[PartMake] = Json.format[PartMake]
}
