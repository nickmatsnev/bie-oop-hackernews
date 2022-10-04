package commands

import api.apicalls.ApiService
import views.View

object ItemCommand extends Command {
  override def execute(id: Any): Unit = {
    val idInt = id.asInstanceOf[Int]
    val itemObj = new ApiService().getItem(idInt)
    if(itemObj.isEmpty){
      throw new NoSuchElementException("Item " + idInt + " doesn't exist")
    }
    val item = itemObj.get
    View.viewItem(item)
    //CommentCommand.execute(itemObj)
  }

  override def showHelp() : Unit = View.viewHelp("item")
}
