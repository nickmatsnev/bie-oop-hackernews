package api.objects

import upickle.default.{ReadWriter => RW, macroRW}

/**
 * @param items
 * @param profiles
 */
case class UpdatesObject(items: Array[Int] = Array(),
                         profiles: Array[String] = Array())

/**
 *
 */
object UpdatesObject{
  implicit val rw: RW[UpdatesObject] = macroRW
}