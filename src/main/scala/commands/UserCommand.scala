package commands

import api.apicalls.ApiService
import views.View

/**
 * executes user command
 */
object UserCommand extends Command {
  /**
   * @param id
   * @param options
   */
  override def execute(id: Any, options: CommandOptions): Unit = {
    val apiService = new ApiService()
    val idStr = id.toString
    apiService.setTtlAndValidate(options.ttl, idStr, "user")
    val userObj = apiService.getUser(idStr)
    if(userObj.isEmpty) throw new NoSuchElementException("User " + idStr + " doesn't exist")
    val user = userObj.get
    View.viewUser(user)
  }

  /**
   * shows help
   */
  override def showHelp() : Unit = View.viewHelp("user")
}
