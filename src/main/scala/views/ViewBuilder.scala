package views

import api.objects.{ItemObject, UserObject}
import time.TimeBuilder
import time.enums.{Month, Weekday}

import scala.Console.{BOLD, RESET}
import scala.util.matching.Regex

/**
 *
 */
object ViewBuilder {

  /**
   * @param text
   * @return
   */
  private def fromHTML(text: String): String = {
    val noPTags = text.replaceAll("<p>", "\n")

    val iStart:       Regex = "(<i>|<em>|<b>|<strong>)(.*)".r // i have not found italics for scala
    val iEnd:    Regex = "(</i>|</em>|</b>|</strong>)(.*)".r
    /*
    val iStartResult = iStart.findFirstIn(noPTags)
    if (iStartResult.isEmpty) return noPTags
    val iStartCounter: Int = iStartResult.get.toInt

    val leftPart = noPTags.splitAt(iStartCounter)._1
    val iPartAndRightPart = noPTags.splitAt(iStartCounter)._2

    val iEndResult = iEnd.findFirstIn(iPartAndRightPart)
    if (iEndResult.isEmpty) return noPTags
    val iEndCounter = iEndResult.get.toInt

    val iPart = bold(noPTags.splitAt(iEndCounter)._1)
    val rightPart = noPTags.splitAt(iEndCounter)._2

    leftPart.concat(iPart.concat(rightPart))
     */
    noPTags
  }


  /**
   * @param text
   * @return
   */
  private def bold(text: String): String = s"$RESET$BOLD$text$RESET"

  /**
   * @param itemObj
   * @return
   */
  def buildItemView(itemObj: ItemObject): String = {
    itemObj.itemType match {
      case "story" => buildStory(itemObj)
      case "comment" => buildComment(itemObj)
      case "poll" => buildPoll(itemObj)
      case "pollopt" => buildPollOpt(itemObj)
      case _ =>
        print("Item type is not defined. It is " + itemObj.itemType)
        buildStory(itemObj)
    }
  }

  /**
   * @param userObj
   * @return
   */
  def buildUserView(userObj: UserObject): String = buildUser(userObj)

  /**
   * @param itemObj
   * @return
   */
  def buildStory(itemObj: ItemObject): String = {
    var storyString = bold(itemObj.title) + " (" + itemObj.url + ")\n"
    storyString += itemObj.score + " points by " + bold(itemObj.by)
    storyString += " at " + buildTime(itemObj.time) + " "
    storyString += " | " + bold(itemObj.kids.length.toString) + " comments\n"
    storyString
  }

  /**
   * @param itemObj
   * @return
   */
  def buildComment(itemObj: ItemObject): String = {
    var commentString = "Comment by " + bold(itemObj.by) + " at " + buildTime(itemObj.time) + ":\n"
    commentString += fromHTML(itemObj.text) + "\n"
    commentString += bold(itemObj.kids.length.toString) + " comments below\n"
    commentString
  }

  /**
   * @param itemObj
   * @return
   */
  def buildPoll(itemObj: ItemObject): String = {
    var pollString = itemObj.title + " by " + itemObj.by + " at " + buildTime(itemObj.time) + ":\n"
    pollString += "total comment count:" + itemObj.descendants + "\n"
    pollString += "score: " + itemObj.score + "\n"
    pollString += "comments' count: " + itemObj.kids.length + "\n"
    pollString
  }

  /**
   * @param itemObj
   * @return
   */
  def buildPollOpt(itemObj: ItemObject): String = {
    var polloptString = bold(itemObj.score.toString) + " points by " + bold(itemObj.by)
    polloptString += " at " + buildTime(itemObj.time) + ":\n"
    polloptString += fromHTML(itemObj.text) + "\n"
    polloptString
  }

  /**
   * @param userObj
   * @return
   */
  def buildUser(userObj: UserObject): String = {
    var userString = "name: " + bold(userObj.id) + "\n"
    userString += "created at: " + buildTime(userObj.created) + "\n"
    userString += "about: " + fromHTML(userObj.about) + "\n"
    userString += "karma: " + bold(userObj.karma.toString) + "\n"
    userString += "comments amd stories spawned" +
      ": " + bold(userObj.submitted.length.toString) + "\n"
    userString
  }

  /**
   * @param weekDay
   * @return
   */
  def buildWeekDay(weekDay: Int): String = {
    var weekDayString = ""
    Weekday.values.foreach {
      case d if d.id == weekDay =>
        weekDayString = d.toString
      case _ => "Weekday like this does not exist."
    }
    weekDayString
  }

  /**
   * @param month
   * @return
   */
  def buildMonth(month: Int): String = {
    Month.apply(month)
  }

  /**
   * @param seconds
   * @return
   */
  def buildTime(seconds: Long): String = {
    val dateTime = TimeBuilder.getTime(seconds)
    val weekDay = TimeBuilder.getWeekDay(dateTime)
    dateTime.toString + ", " + buildWeekDay(weekDay)
  }

  /**
   * @param commandType
   * @return
   */
  def buildHelp(commandType : String): String ={
    var help = "Help for " + commandType + ":\n"
    // match for diff command
    commandType match {
      case "comment" => help += "Comment command shows all comments to the item recursively.\n"
      case "item" => help += "Item is called and therefore it prints basic info about unitary item.\n"
      case "user" => help += "Shows user's info:\nname;\ndate of creation;\ncarma;\nposted stuff in a human quantity.\n"
      case "newstories" | "beststories" | "showstories" | "askstories" | "jobstories" | "topstories" =>
          help += "Summons list of best, new, top, job, ask or show stories.\n"
          help += "Command Options:\n1. --page=[value] opens the specific page of stories\n2. --showSize=[value] allows limit" +
          "ed to [value] number of rows\n3. --showTime=[valueInSeconds] shows the time each page of items will be static.\n4. --start=[k]&--end==[n] where k <= n and k is the first page and n is the last" +
          " page of selected topic.\n"
      case _ => help = "This command type is unknown for me.\n"
    }
    help += "End of the helping section.\n\n"
    help
  }
}
