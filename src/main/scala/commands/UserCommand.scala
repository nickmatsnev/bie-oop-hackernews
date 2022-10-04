package commands

import api.apicalls.ApiService
import views.View

object UserCommand extends Command {
  override def execute(id: Any): Unit = {
    val idStr = id.toString
    val userObj = new ApiService().getUser(idStr)
    if(userObj.isEmpty){
      throw new NoSuchElementException("User " + idStr + " doesn't exist")
    }
    val user = userObj.get
    View.viewUser(user)
  }

  override def showHelp() : Unit = View.viewHelp("user")
}
