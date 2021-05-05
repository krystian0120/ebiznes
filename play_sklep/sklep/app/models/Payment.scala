package models

import play.api.libs.json.{Json, OFormat}

case class Payment(paymentId: String, paymentMethod: String)

object Payment {
  implicit val paymentFormat: OFormat[Payment] = Json.format[Payment]
}
