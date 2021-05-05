package models

import play.api.libs.json.{Json, OFormat}

case class PartCategory(categoryId: String, name: String)

object PartCategory {
  implicit val partCategoryFormat: OFormat[PartCategory] = Json.format[PartCategory]
}
