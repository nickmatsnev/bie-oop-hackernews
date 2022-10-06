package commands

import api.apicalls.ApiService
import views.View

object CommentCommand extends Command{
  override def execute(id: Any, options: CommandOptions): Unit = {
    val idInt = id.asInstanceOf[Int]
    val itemObj = new ApiService().getItem(idInt)
    val item = itemObj.get
    val commentsIds = item.kids
    var lazyFetchingCounter: Int = 0
    for (commentId <- commentsIds) {
      lazyFetchingCounter += 1
      if (lazyFetchingCounter % options.showSize == 0) Thread.sleep(options.showTime)
      ItemCommand.execute(commentId, options)
    }
  }
  override def showHelp() : Unit = View.viewHelp("comment")

}
