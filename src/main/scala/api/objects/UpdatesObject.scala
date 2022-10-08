package api.objects

import upickle.default.{ReadWriter => RW, macroRW}

/**
 * @param items list of updated items
 * @param profiles list of updated user profiles
 */
case class UpdatesObject(items: Array[Int] = Array(),
                         profiles: Array[String] = Array())

/**
 *
 */
object UpdatesObject{
  implicit val rw: RW[UpdatesObject] = macroRW
}