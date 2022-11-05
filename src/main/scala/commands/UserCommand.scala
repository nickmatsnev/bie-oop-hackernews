package commands

import api.apicalls.ApiService
import cache.CacheService
import views.View

/**
 * executes user command
 */
class UserCommand(val apiService: ApiService) extends Command {
  /**
   * precondition: ID should be string frankly speaking
   * post condition: prints user
   * @param id
   * @param options
   */
  override def execute(id: Any, options: CommandOptions): Unit = {
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
