import api.apicalls.ApiService
import cache.{CacheFile, CacheService}
import commands.{CommandOptions, StoriesCommand, UserCommand}

/**
 *
 */
class CommandFactory(cacheService: CacheService, apiService: ApiService) {
  /**
   * @param commandName - passed command to execute
   * @param options - set of options
   */
  def create(commandName: String, options : Array[String]): Unit = {
    var start: Int = -1
    var end: Int = -1
    var page: Int = -1
    var ttl: Int = 600
    var withComments: Int = 0 // when 1 with comments
    var showSize: Int = 10 // number of elements shown
    var showTime: Int = 10000 // miliseconds to watch
    if (!options.isEmpty){
      for (option <- options) {
        val optionDef = option.split('=').head
        val optionVal = option.split('=').drop(1).head
        optionDef match {
          case "--start" => start = optionVal.toInt
          case "--end" => end = optionVal.toInt
          case "--page" => page = optionVal.toInt
          case "--ttl" => ttl = optionVal.toInt
          case "--withComments" => withComments = optionVal.toInt
          case "--showSize" => showSize = optionVal.toInt
          case "--showTime" => showTime = optionVal.toInt
          case _ => throw new Exception("Option " + optionDef + " is not defined.")
        }
      }
    }
    val cOptions = CommandOptions(start, end, page, ttl, withComments, showSize + 1, showTime * 1000)
    commandName match {
      case "newstories" | "beststories" | "showstories" | "askstories" | "jobstories" | "topstories" =>
        val storiesCommand = new StoriesCommand(apiService)
        storiesCommand.execute(commandName, cOptions)
      case _ =>
        val userCommand = new UserCommand(apiService)
        userCommand.execute(commandName, cOptions)
    }
  }
}
