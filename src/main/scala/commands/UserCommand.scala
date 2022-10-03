package commands

import api.apicalls.ApiService
import views.View

object UserCommand {
  def execute(id: String): Unit = {
    val userObj = new ApiService().getUser(id)
    if(userObj.isEmpty){
      throw new NoSuchElementException("User " + id + " doesn't exist")
    }
    val user = userObj.get
    View.viewUser(user)
  }

  def showHelp() : Unit = println("Help about users will be here.\n")
}
