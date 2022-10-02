package views.html

import api.objects.{ItemObject, UserObject}
import time.TimeBuilder
import time.enums.{Month, Weekday}



object ViewBuilder {

  def buildItemView(itemObj : ItemObject) : String = {
    itemObj.itemType match {
      case "story" => buildStory(itemObj)
      case "comment" => buildComment(itemObj)
      case "poll" => buildPoll(itemObj)
      case "pollopt" => buildPollOpt(itemObj)
      case _ => throw new Exception("Item type is not defined.")
    }
  }

  def buildUserView(userObj : UserObject) : String = buildUser(userObj)

  def buildStory(itemObj : ItemObject) : String = {
    var storyString = itemObj.title + "(" + itemObj.url + ")\n"
    storyString += itemObj.score + " points by " + itemObj.by
    storyString += " | " + itemObj.kids.length + " comments\n"
    storyString
  }

  def buildComment(itemObj : ItemObject) : String = {
    var commentString = "Comment by " + itemObj.by + ":\n"
    commentString += itemObj.htmlText += "\n"
    commentString += itemObj.kids.length + " comments below\n"
    commentString
  }

  def buildPoll(itemObj : ItemObject) : String = {
    var pollString = itemObj.title + " by " + itemObj.by + "\n"
    pollString += "total comment count:" + itemObj.descendants + "\n"
    pollString += "score: " + itemObj.score + "\n"
    pollString += "comments' count: " + itemObj.kids.length + "\n"
    pollString
  }

  def buildPollOpt(itemObj : ItemObject) : String = {
    var polloptString = itemObj.score + " points by " + itemObj.by + "\n"
    polloptString += itemObj.htmlText + "\n"
    polloptString
  }

  def buildUser(userObj : UserObject) : String = {
    var userString = "name: " + userObj.userid + "\n"
    userString += "created: " + buildTime(userObj.created) + "\n"
    userString += "karma: " + userObj.karma + "\n"
    userString += "submitted items count: " + userObj.submitted.length + "\n"
    userString
  }

  def buildWeekDay(weekDay : Int) : String = {
    var weekDayString = ""
    Weekday.values.foreach
    {
      case d if d.id == weekDay =>
        weekDayString = d.toString
      case _ => "Weekday like this does not exist."
    }
    weekDayString
  }

  def buildMonth(month : Int) : String = {
    var monthString = ""
    Month.values.foreach
    {
      case d if d.id == month  =>
        monthString = d.toString
      case _ => "Month like this does not exist."
    }
    monthString
  }

  def buildTime(seconds : Long): String ={
    val dateTime = TimeBuilder.getTime(seconds)
    val weekDay = TimeBuilder.getWeekDay(dateTime)
    dateTime.toString + ", " + weekDay
  }
}

