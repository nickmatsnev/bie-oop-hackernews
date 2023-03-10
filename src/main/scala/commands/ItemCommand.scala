package commands

import api.apicalls.ApiService
import views.View

/**
 * executes item command
 */
class ItemCommand(val apiService: ApiService) extends Command {
  /**
   * precondition: ID should be integer frankly speaking
   * post condition: prints item
   * @param id
   * @param options
   */
  override def execute(id: Any, options: CommandOptions): Unit = {
    apiService.setTtlAndValidate(options.ttl, id.toString, "item")
    val idInt = id.asInstanceOf[Int]
    val itemObj = apiService.getItem(idInt)
    if(itemObj.isEmpty) throw new NoSuchElementException("Item " + idInt + " doesn't exist")
    val item = itemObj.get
    View.viewItem(item)
    if(options.withComments == 1) {
      val commentCommand = new CommentCommand(apiService)
      commentCommand.execute(itemObj, options)
    }
  }

  /**
   * shows help
   */
  override def showHelp() : Unit = View.viewHelp("item")
}