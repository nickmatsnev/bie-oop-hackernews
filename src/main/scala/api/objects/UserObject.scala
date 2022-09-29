package api.objects

//Field	Description
//id	The user's unique username. Case-sensitive. Required.
//created	Creation date of the user, in Unix Time.
//karma	The user's karma.
//about	The user's optional self-description. HTML.
//submitted	List of the user's stories, polls and comments.
case class UserObject(userid : Int,
                      created : String,
                      karma : Int,
                      aboutHTML : Int,
                      submitted : Array[Int]){}