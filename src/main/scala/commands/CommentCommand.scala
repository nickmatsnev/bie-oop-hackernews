package commands

import api.apicalls.ApiService
import cache.CacheService
import views.View

/**
 * executes comment command
 */
class CommentCommand(val apiService: ApiService) extends Command{
  /**
   * @param id
   * @param options
   */
  override def execute(id: Any, options: CommandOptions): Unit = {
    val itemCommand = new ItemCommand(apiService)
    val idInt = id.asInstanceOf[Int]
    val itemObj = apiService.getItem(idInt)
    val item = itemObj.get
    val commentsIds = item.kids
    var lazyFetchingCounter: Int = 0
    for (commentId <- commentsIds) {
      lazyFetchingCounter += 1
      if (lazyFetchingCounter % options.showSize == 0) Thread.sleep(options.showTime)
      itemCommand.execute(commentId, options)
    }
  }

  /**
   * shows help
   */
  override def showHelp() : Unit = View.viewHelp("comment")

}
