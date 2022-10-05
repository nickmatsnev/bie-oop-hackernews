package commands

import api.apicalls.ApiService
import views.View

object StoriesCommand extends Command{
  def execute(id : Any) : Unit = {
    val apiService = new ApiService()
    val storyType = id.toString
    val storyTypeStr = storyType.toString
    var storiesIds = apiService.getTopStories // case _
    storyTypeStr match {
      case "topstories" => storiesIds = apiService.getTopStories
      case "newstories" => storiesIds = apiService.getNewStories
      case "beststories" => storiesIds = apiService.getBestStories
      case "askstories" => storiesIds = apiService.getAskStories
      case "showstories" => storiesIds = apiService.getShowStories
      case "jobstories" => storiesIds = apiService.getJobStories
    }
    for (storyId <- storiesIds) {
      ItemCommand.execute(storyId)
    }
  }

  override def showHelp() : Unit = View.viewHelp("stories")
}
