import CommandParser.{defaultHelp, splitCommand}
import cache.CacheService
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
      CacheService.clearCache()
      return
    }
    val cf = new CommandFactory
    cf.create(commandName, commandOptions)
  }
}
