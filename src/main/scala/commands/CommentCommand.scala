package commands

import api.objects.ItemObject

object CommentCommand extends Command {
  def execute(itemObj: ItemObject): Unit = {
    val commentsIds = itemObj.kids
    for (commentId <- commentsIds) {
      ItemCommand.execute(commentId)
    }
  }
  def showHelp() : Unit = println("Help about comments will be here.\n")
}
