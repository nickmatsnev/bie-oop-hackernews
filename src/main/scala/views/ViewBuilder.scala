package views

import api.objects.{ItemObject, UserObject}
import time.TimeBuilder
import time.enums.{Month, Weekday}

import scala.Console.{BOLD, RESET}

object ViewBuilder {

  //def removeZero(s: String) = s.map(c => if(c == '0') ' ' else c)

  private def fromHTML(text: String): String = {
    //<p> - newline. done
    val noPTags = text.replaceAll("<p>", "\n")
    // <i>/<em> - italics.
    // go thru text if meet <i> we save all before it as leftPart
    // then make it cursivePart before we meet </i> and the same with <em>
    // and each time we add it to new text when we are done with the tag;
    // i assume the same algorithm proceeds for bold text.
    var counter = 0
    var noITags = ""
    var counterWhereStarts: Array[String] = Array()
    var counterWhereEnds: Array[String] = Array()
    var piecesToBeItalic: Array[String] = Array()
    var foundTag: Boolean = false
    for(letter <- noPTags){
      if(letter == '<'){
        if (noPTags.charAt(counter + 1) == 'i' && noPTags.charAt(counter + 2) == '>'){

        }
      }
      noITags += letter
      counter += 1
    }
    // <b>/<strong> - bold.
    noPTags
  }

  private def bold(text: String): String = s"$RESET$BOLD$text$RESET"

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

  def buildUserView(userObj: UserObject): String = buildUser(userObj)

  def buildStory(itemObj: ItemObject): String = {
    var storyString = bold(itemObj.title) + " (" + itemObj.url + ")\n"
    storyString += itemObj.score + " points by " + bold(itemObj.by)
    storyString += " at " + buildTime(itemObj.time) + " "
    storyString += " | " + bold(itemObj.kids.length.toString) + " comments\n"
    storyString
  }

  def buildComment(itemObj: ItemObject): String = {
    var commentString = "Comment by " + bold(itemObj.by) + " at " + buildTime(itemObj.time) + ":\n"
    commentString += itemObj.text + "\n" // TODO HTML
    commentString += bold(itemObj.kids.length.toString) + " comments below\n"
    commentString
  }

  def buildPoll(itemObj: ItemObject): String = {
    var pollString = itemObj.title + " by " + itemObj.by + " at " + buildTime(itemObj.time) + ":\n"
    pollString += "total comment count:" + itemObj.descendants + "\n"
    pollString += "score: " + itemObj.score + "\n"
    pollString += "comments' count: " + itemObj.kids.length + "\n"
    pollString
  }

  def buildPollOpt(itemObj: ItemObject): String = {
    var polloptString = bold(itemObj.score.toString) + " points by " + bold(itemObj.by)
    polloptString += " at " + buildTime(itemObj.time) + ":\n"
    polloptString += itemObj.text + "\n" // TODO HTML
    polloptString
  }

  def buildUser(userObj: UserObject): String = {
    var userString = "name: " + bold(userObj.id) + "\n"
    userString += "created at: " + buildTime(userObj.created) + "\n"
    userString += "about: " + userObj.about + "\n" // TODO HTML
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
