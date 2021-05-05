package models

import play.api.libs.json.{Json, OFormat}

case class Part(partId: String, categoryId: String, name: String)

object Part {
  implicit val partFormat: OFormat[Part] = Json.format[Part]
}
