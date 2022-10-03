package commands.stories

import api.apicalls.ApiService
import views.html.View

object StoryCommand {
  def execute(itemId : Int): Unit = {
    val itemObj = new ApiService().getItem(itemId)
    View.viewStory(itemObj)
  }
}
