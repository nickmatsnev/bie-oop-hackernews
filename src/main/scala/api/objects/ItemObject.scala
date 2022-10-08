package api.objects

import upickle.default.{macroRW, ReadWriter => RW}
import upickle.implicits.key

/**
 * @param id The item's unique id.
 * @param deleted true if the item is deleted.
 * @param itemType The type of item. One of "job", "story", "comment", "poll", or "pollopt".
 * @param by The username of the item's author.
 * @param time Creation date of the item, in Unix Time.
 * @param text The comment, story or poll text. HTML.
 * @param dead true if the item is dead.
 * @param parent The comment's parent: either another comment or the relevant story.
 * @param poll The pollopt's associated poll.
 * @param kids The ids of the item's comments, in ranked display order.
 * @param url	The URL of the story.
 * @param score	The story's score, or the votes for a pollopt.
 * @param title	The title of the story, poll or job. HTML.
 * @param parts	A list of related pollopts, in display order.
 * @param descendants	In the case of stories or polls, the total comment count.
 */
case class ItemObject(id : Int,
                      deleted : Boolean = false,
                      @key("type") itemType : String = "Unknown type",
                      by : String = "Unknown Author",
                      time : Long = 0,
                      text : String = "No text",
                      dead : Boolean = false,
                      parent : Int = -1,
                      poll : Int = -1,
                      kids : Array[Int] = Array(),
                      url : String = "No url",
                      score : Int = -1,
                      title : String = "No title",
                      parts : Array[Int] = Array(),
                      descendants: Int = -1) {

}

/**
 *
 */
object ItemObject{
  implicit val rw: RW[ItemObject] = macroRW
}