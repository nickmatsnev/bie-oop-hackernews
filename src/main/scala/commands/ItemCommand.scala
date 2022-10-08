package commands

import api.apicalls.ApiService
import views.View

/**
 * executes item command
 */
object ItemCommand extends Command {
  /**
   * @param id
   * @param options
   */
  override def execute(id: Any, options: CommandOptions): Unit = {
    val apiService = new ApiService()
    apiService.setTtlAndValidate(options.ttl, id.toString, "item")

    val idInt = id.asInstanceOf[Int]
    val itemObj = apiService.getItem(idInt)
    if(itemObj.isEmpty) throw new NoSuchElementException("Item " + idInt + " doesn't exist")
    val item = itemObj.get
    View.viewItem(item)
    if(options.withComments == 1) CommentCommand.execute(itemObj, options)
  }

  /**
   * shows help
   */
  override def showHelp() : Unit = View.viewHelp("item")
}
