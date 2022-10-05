package api.objects

import upickle.default.{ReadWriter => RW, macroRW}

case class UpdatesObject(items: Array[Int] = Array(),
                         profiles: Array[String] = Array())
object UpdatesObject{
  implicit val rw: RW[UpdatesObject] = macroRW
}