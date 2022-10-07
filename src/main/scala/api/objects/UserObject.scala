package api.objects

import upickle.default.{ReadWriter => RW, macroRW}

/**
 * @param id
 * @param created
 * @param karma
 * @param about
 * @param submitted
 */
//Field	Description
//id	The user's unique username. Case-sensitive. Required.
//created	Creation date of the user, in Unix Time.
//karma	The user's karma.
//about	The user's optional self-description. HTML.
//submitted	List of the user's stories, polls and comments.
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