package views

import api.objects.{ItemObject, UserObject}
import time.TimeBuilder
import time.enums.{Month, Weekday}

import scala.Console.{BOLD, RESET}

object ViewBuilder {

  private def bold(text: String): String = s"$RESET$BOLD$text$RESET"

  def buildItemView(itemObj: ItemObject): String = {
    itemObj.itemType match {
      case "story" => buildStory(itemObj)
      case "comment" => buildComment(itemObj)
      case "poll" => buildPoll(itemObj)
      case "pollopt" => buildPollOpt(itemObj)
      case _ => throw new Exception("Item type is not defined.")
    }
  }

  def buildUserView(userObj: UserObject): String = buildUser(userObj)

  def buildStory(itemObj: ItemObject): String = {
    var storyString = bold(itemObj.title) + " (" + itemObj.url + ")\n"
    storyString += itemObj.score + " points by " + bold(itemObj.by)
    storyString += " at " + buildTime(itemObj.time) + " "
    storyString += " | " + bold(itemObj.kids.length.toString) + " comments\n"
    storyString
  }

  def buildComment(itemObj: ItemObject): String = {
    var commentString = "Comment by " + bold(itemObj.by) + ":\n"
    commentString += itemObj.text + "\n"
    commentString += bold(itemObj.kids.length.toString) + " comments below\n"
    commentString
  }

  def buildPoll(itemObj: ItemObject): String = {
    var pollString = itemObj.title + " by " + itemObj.by + "\n"
    pollString += "total comment count:" + itemObj.descendants + "\n"
    pollString += "score: " + itemObj.score + "\n"
    pollString += "comments' count: " + itemObj.kids.length + "\n"
    pollString
  }

  def buildPollOpt(itemObj: ItemObject): String = {
    var polloptString = bold(itemObj.score.toString) + " points by " + bold(itemObj.by) + "\n"
    polloptString += itemObj.text + "\n"
    polloptString
  }

  def buildUser(userObj: UserObject): String = {
    var userString = "name: " + bold(userObj.id) + "\n"
    userString += "created at: " + buildTime(userObj.created) + "\n"
    userString += "karma: " + bold(userObj.karma.toString) + "\n"
    userString += "comments amd stories spawned" +
      ": " + bold(userObj.submitted.length.toString) + "\n"
    userString
  }

  def buildWeekDay(weekDay: Int): String = {
    var weekDayString = ""
    Weekday.values.foreach {
      case d if d.id == weekDay =>
        weekDayString = d.toString
      case _ => "Weekday like this does not exist."
    }
    weekDayString
  }

  def buildMonth(month: Int): String = {
    Month.apply(month)
  }

  def buildTime(seconds: Long): String = {
    val dateTime = TimeBuilder.getTime(seconds)
    val weekDay = TimeBuilder.getWeekDay(dateTime)
    dateTime.toString + ", " + buildWeekDay(weekDay)
  }

  def buildHelp(commandType : String): String ={
    var help = "Help for " + commandType + ":\n"
    // match for diff command
    commandType match {
      case "comment" => help += "Comment command shows all comments to the item recursively.\n"
      case "item" => help += "Item is called and therefore it prints basic info about unitary item.\n"
      case "user" => help += "Shows user's info:\nname;\ndate of creation;\ncarma;\nposted stuff in a human quantity.\n"
      case "stories" => help += "Summons list of best, new, top, job, ask or show stories.\n"
      case _ => help = "This command type is unknown for me.\n"
    }
    help
  }
}
