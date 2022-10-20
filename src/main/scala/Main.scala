import CommandParser.{defaultHelp, splitCommand}
import api.apicalls.ApiService
import cache.{CacheFile, CacheService}
import views.View

/**
 *
 */
object Main {

  /**
   * @param args - [--option] [command] [--command-options]
   */
  def main(args: Array[String]): Unit = {
    val receivedArgs = splitCommand(args)
    val options = receivedArgs._1
    val commandOptions = receivedArgs._2.drop(1)
    val commandName = receivedArgs._2.head

    for (co <- commandOptions) println(co)
    if (options.contains("--help") || commandName == "--help"){
      if (args.length == 1) {
        print(defaultHelp())
        return
      } else
        View.viewHelp(commandName)
    }
    if(options.contains("--clearCache") || commandName == "--clearCache") {
      CacheFile.clearCache()
      return
    }
    val cacheService = new CacheService(CacheFile)
    val apiService = new ApiService(cacheService)
    val cf = new CommandFactory(cacheService, apiService)
    cf.create(commandName, commandOptions)
  }
}
