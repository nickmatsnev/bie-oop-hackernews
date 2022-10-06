package commands

import api.apicalls.ApiService
import cache.Cache
import views.View

object ItemCommand extends Command {
  override def execute(id: Any, options: CommandOptions): Unit = {
    val apiService = new ApiService()
    apiService.setTtl(options.ttl)

    val idInt = id.asInstanceOf[Int]
    val itemObj = apiService.getItem(idInt)
    if(itemObj.isEmpty) throw new NoSuchElementException("Item " + idInt + " doesn't exist")
    val item = itemObj.get
    View.viewItem(item)
    if(options.withComments == 1) CommentCommand.execute(itemObj, options)
  }

  override def showHelp() : Unit = View.viewHelp("item")
}
