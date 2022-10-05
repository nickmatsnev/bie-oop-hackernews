import CommandParser.{defaultHelp, splitCommand}
import api.apicalls.ApiCallsImplementation
import commands.{ItemCommand, StoriesCommand, UserCommand}
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
    if (options.contains("--help")){
      if (args.length == 1)
        print(defaultHelp())
      else
        View.viewHelp(commandName)
    }
    val cf = new CommandFactory
    cf.create(commandName)

    // observable tasks and not all of em i have written in the note book:
    // 1. add flags for help so it'll be shown from views. done
    // 2. cache.
    // 3. options.
    // 4. lazy fetching.
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
