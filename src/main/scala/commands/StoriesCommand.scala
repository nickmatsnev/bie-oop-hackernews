package commands

import api.apicalls.ApiService
import views.View

object StoriesCommand extends Command{
  def execute(id : Any) : Unit = {
    val storyType = id.toString
    val storyTypeStr = storyType.toString
    var storiesIds = new ApiService().getTopStories // case _
    storyTypeStr match {
      case "topstories" => storiesIds = new ApiService().getTopStories
      case "newstories" => storiesIds = new ApiService().getNewStories
      case "beststories" => storiesIds = new ApiService().getBestStories
      case "askstories" => storiesIds = new ApiService().getAskStories
      case "showstories" => storiesIds = new ApiService().getShowStories
      case "jobstories" => storiesIds = new ApiService().getJobStories
    }
    for (storyId <- storiesIds) {
      ItemCommand.execute(storyId)
    }
  }

  override def showHelp() : Unit = View.viewHelp("stories")
}
