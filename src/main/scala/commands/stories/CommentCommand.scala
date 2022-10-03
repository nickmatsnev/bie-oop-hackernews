package commands.stories

import api.apicalls.{ApiCalls, ApiService}
import api.objects.ItemObject

object CommentCommand {
  def execute(itemObj : ItemObject) : Unit = {
    val commentsIds = itemObj.kids
    for (commentId <- commentsIds){
      ItemCommand.execute(commentId)
    }
  }
}
