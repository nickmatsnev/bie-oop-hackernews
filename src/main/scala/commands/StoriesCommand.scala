package commands

import api.apicalls.ApiService
import views.View

object StoriesCommand {
  def execute(storyType : String) : Unit = {
    var storiesIds = new ApiService().getTopStories // case _
    storyType match {
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
}
