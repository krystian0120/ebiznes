package models

import play.api.libs.json.{Json, OFormat}

case class User(userId: String, name: String, surname: String, email: String)

object User {
  implicit val userFormat: OFormat[User] = Json.format[User]
}
