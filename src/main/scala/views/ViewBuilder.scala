package views

import api.objects.{ItemObject, UserObject}
import time.TimeBuilder
import time.enums.{Month, Weekday}

import scala.Console.{BOLD, RESET, UNDERLINED}

/**
 *
 */
object ViewBuilder {

  /**
   * @param text where we look for substring
   * @param substring which we need to find
   * @return index of the substring in the text
   */
  private def getIndexOfSubString(text:String,substring:String):Int={
      text.indexOf(substring)
  }


  /**
   * @param text with html tags or without them
   * @return text without html tags and the text is shown in accordance with how these tags should've looked on web
   */
  def fromHTML(text: String): String = {
    val noPTags = text.replaceAll("<p>", "\n")

    val iStart: Int = getIndexOfSubString(noPTags, "<i>")
    var noITags = noPTags
    if (iStart != -1){
      val leftPart =  noPTags.splitAt(iStart)._1
      val iPartRightPart = noPTags.splitAt(iStart)._2.drop(3)
      val iEnd: Int = getIndexOfSubString(iPartRightPart, "</i>")
      val iPart = iPartRightPart.splitAt(iEnd)._1
      val rightPart = iPartRightPart.splitAt(iEnd )._2.drop(4)
      noITags = leftPart.concat(italic(iPart).concat(rightPart))
    }
    val bStart: Int = getIndexOfSubString(noPTags, "<b>")
    var noBTags = noITags
    if (bStart != -1){
      val leftPart =  noITags.splitAt(bStart)._1
      val bPartRightPart = noITags.splitAt(bStart)._2.drop(3)
      val bEnd: Int = getIndexOfSubString(bPartRightPart, "</b>")
      val bPart = bPartRightPart.splitAt(bEnd)._1
      val rightPart = bPartRightPart.splitAt(bEnd)._2.drop(4)
      noBTags = leftPart.concat(bold(bPart).concat(rightPart))
    }
    val emStart: Int = getIndexOfSubString(noPTags, "<em>")
    var noEmTags = noBTags
    if (emStart != -1){
      val leftPart =  noBTags.splitAt(emStart)._1
      val emPartRightPart = noBTags.splitAt(emStart)._2.drop(4)
      val emEnd: Int = getIndexOfSubString(emPartRightPart, "</em>")
      val emPart = emPartRightPart.splitAt(emEnd)._1
      val rightPart = emPartRightPart.splitAt(emEnd)._2.drop(5)
      noEmTags = leftPart.concat(italic(emPart).concat(rightPart))
    }
    val sStart: Int = getIndexOfSubString(noPTags, "<strong>")
    var noSTags = noEmTags
    if (sStart != -1){
      val leftPart =  noEmTags.splitAt(sStart)._1
      val sPartRightPart = noEmTags.splitAt(sStart)._2.drop(8)
      val sEnd: Int = getIndexOfSubString(sPartRightPart, "</strong>")
      val sPart = sPartRightPart.splitAt(sEnd)._1
      val rightPart = sPartRightPart.splitAt(sEnd)._2.drop(9)
      noSTags = leftPart.concat(bold(sPart).concat(rightPart))
    }
    noSTags
  }


  /**
   * @param text
   * @return italic text
   */
  private def italic(text: String): String = s"$RESET$UNDERLINED$text$RESET"

  /**
   * @param text
   * @return bold text
   */
  private def bold(text: String): String = s"$RESET$BOLD$text$RESET"

  /**
   * @param itemObj
   * @return wrapped for presentation item object
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
   * @return wrapped for presentation user object
   */
  def buildUserView(userObj: UserObject): String = buildUser(userObj)

  /**
   * @param itemObj
   * @return wrapped story
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
   * @return wrapped comment
   */
  def buildComment(itemObj: ItemObject): String = {
    var commentString = "Comment by " + bold(itemObj.by) + " at " + buildTime(itemObj.time) + ":\n"
    commentString += fromHTML(itemObj.text) + "\n"
    commentString += bold(itemObj.kids.length.toString) + " comments below\n"
    commentString
  }

  /**
   * @param itemObj
   * @return wrapped poll
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
   * @return wrapped pollopt
   */
  def buildPollOpt(itemObj: ItemObject): String = {
    var polloptString = bold(itemObj.score.toString) + " points by " + bold(itemObj.by)
    polloptString += " at " + buildTime(itemObj.time) + ":\n"
    polloptString += fromHTML(itemObj.text) + "\n"
    polloptString
  }

  /**
   * @param userObj
   * @return  wrapped user
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
   * @return selected month in String
   */
  def buildMonth(month: Int): String = {
    Month.apply(month)
  }

  /**
   * @param seconds
   * @return time nicely wrapped
   */
  def buildTime(seconds: Long): String = {
    val dateTime = TimeBuilder.getTime(seconds)
    val weekDay = TimeBuilder.getWeekDay(dateTime)
    dateTime.toString + ", " + buildWeekDay(weekDay)
  }

  /**
   * @param commandType
   * @return help for a command
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
