package api.objects

import upickle.default.{ReadWriter => RW, macroRW}
//Field	Description
//id	The user's unique username. Case-sensitive. Required.
//created	Creation date of the user, in Unix Time.
//karma	The user's karma.
//about	The user's optional self-description. HTML.
//submitted	List of the user's stories, polls and comments.
case class UserObject(id : String,
                      created : Long,
                      karma : Int,
                      about : String,
                      submitted : Array[Int]){
}
object UserObject{
  implicit val rw: RW[UserObject] = macroRW
}