package commands

import api.apicalls.ApiService
import views.View

object CommentCommand extends Command{
  override def execute(id: Any): Unit = {
    val idInt = id.asInstanceOf[Int]
    val itemObj = new ApiService().getItem(idInt)
    val item = itemObj.get
    val commentsIds = item.kids
    for (commentId <- commentsIds) ItemCommand.execute(commentId)
  }
  override def showHelp() : Unit = View.viewHelp("comment")
}
