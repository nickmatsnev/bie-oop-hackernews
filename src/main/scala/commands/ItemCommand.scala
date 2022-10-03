package commands

import api.apicalls.ApiService
import views.View

object ItemCommand {
  def execute(itemId: Int): Unit = {
    val itemObj = new ApiService().getItem(itemId)
    View.viewItem(itemObj)
    //CommentCommand.execute(itemObj)
  }
}
