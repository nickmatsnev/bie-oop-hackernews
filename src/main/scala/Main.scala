import CommandParser.{defaultHelp, splitCommand}
import cache.CacheService
import views.View

object Main {
  // process args
  def main(args: Array[String]): Unit = {
    // You pass any thing at runtime
    // that will be print on the console
    // initiated dev branch
    val receivedArgs = splitCommand(args)
    val options = receivedArgs._1
    val commandOptions = receivedArgs._2.drop(1)
    val commandName = receivedArgs._2.head
    println("command name: " + commandName)
    // i have to put it somewhere else
    println("command options: ")
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

    // observable tasks and not all of em i have written in the note book:
    // 1. add flags for help so it'll be shown from views. done
    // 2. cache. done
    // 3. options. done
    // 4. lazy fetching. done
    // 5. exceptions.
    // 6. look for redundancies.
    // 7. comments.
    // 8. tests.
    // 9. more tests.

    //UserCommand.execute("justin")
    //ItemCommand.execute(8863)
    //StoriesCommand.execute("beststories")
  }

}
