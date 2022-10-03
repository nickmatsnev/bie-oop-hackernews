package commands

import api.apicalls.ApiService
import views.View

object ItemCommand{
  def execute(id: Int): Unit = {
    val itemObj = new ApiService().getItem(id)
    if(itemObj.isEmpty){
      throw new NoSuchElementException("Item " + id + " doesn't exist")
    }
    val item = itemObj.get
    View.viewItem(item)
    //CommentCommand.execute(itemObj)
  }

  def showHelp() : Unit = println("Help about items will be here.\n")
}
