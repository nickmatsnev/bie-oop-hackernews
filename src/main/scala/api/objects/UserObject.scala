package api.objects

import upickle.default.{ReadWriter => RW, macroRW}

/**
 * @param id	The user's unique username. Case-sensitive. Required.
 * @param created 	Creation date of the user, in Unix Time.
 * @param karma	The user's karma.
 * @param about The user's optional self-description. HTML.
 * @param submitted	List of the user's stories, polls and comments.
 */
case class UserObject(id : String = "No name",
                      created : Long = 0,
                      karma : Int = 0,
                      about : String = "No description",
                      submitted : Array[Int] = Array()){
}

/**
 *
 */
object UserObject{
  implicit val rw: RW[UserObject] = macroRW
}