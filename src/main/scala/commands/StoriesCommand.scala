package commands

import api.apicalls.ApiService
import cache.CacheService
import views.View

/**
 * executes story command
 */
class StoriesCommand(val apiService: ApiService) extends Command{

  /**
   * @param counter
   * @param size
   * @param time
   */
  private def fetching(counter : Int, size : Int, time : Int): Unit = if (counter % size == 0) Thread.sleep(time)

  /**
   * precondition: ID should be string frankly speaking
   * post condition: executes stories
   * @param id
   * @param options
   */
  override def execute(id : Any, options: CommandOptions) : Unit = {
    val itemCommand = new ItemCommand(apiService)
    val storyType = id.toString
    val storyTypeStr = storyType
    var storiesIds = apiService.getTopStories
    storyTypeStr match {
      case "topstories" => storiesIds = apiService.getTopStories
      case "newstories" => storiesIds = apiService.getNewStories
      case "beststories" => storiesIds = apiService.getBestStories
      case "askstories" => storiesIds = apiService.getAskStories
      case "showstories" => storiesIds = apiService.getShowStories
      case "jobstories" => storiesIds = apiService.getJobStories
      case  _  => storiesIds = apiService.getTopStories
    }
    var counter = 0
    var lazyFetchingCounter: Int = 1
    for(sId <- storiesIds){
      if (options.end < storiesIds.length){
        // start <= x <= end
        if(options.start > 0 && options.end >= options.start && options.page < 0){
          if (counter >= options.start && counter <= options.end) {
            fetching(lazyFetchingCounter, options.showSize, options.showTime)
            itemCommand.execute(sId, options)
            lazyFetchingCounter += 1
          }
        }
        // end > 0
        if(options.start < 0 && options.end > 0 && options.page < 0){
          if (counter <= options.end){
            fetching(lazyFetchingCounter, options.showSize, options.showTime)
            itemCommand.execute(sId, options)
            lazyFetchingCounter += 1
          }
        }
      }
      // start > 0
      if(options.start > 0 && options.end < 0 && options.page < 0){
        if (counter >= options.start){
          fetching(lazyFetchingCounter, options.showSize, options.showTime)
          itemCommand.execute(sId, options)
          lazyFetchingCounter += 1
        }
      }
      // page > 0
      if(options.start < 0 && options.end < 0 && options.page > 0){
        if (counter == options.page){
          fetching(lazyFetchingCounter, options.showSize, options.showTime)
          itemCommand.execute(sId, options)
          lazyFetchingCounter += 1
        }
      }
      if(options.start < 0 && options.end < 0 && options.page< 0 ){
          itemCommand.execute(sId, options)
      }
      counter += 1
    }
  }

  /**
   * shows help
   */
  override def showHelp() : Unit = View.viewHelp("stories")
}
