package commands

import api.apicalls.ApiService
import views.View

object UserCommand {
  def execute(userId: String): Unit = {
    val userObj = new ApiService().getUser(userId)
    View.viewUser(userObj)
  }
}
