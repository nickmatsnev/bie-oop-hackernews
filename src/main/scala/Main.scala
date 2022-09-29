import CommandParser.{defaultHelp, splitCommand}
import api.ApiCallsImplementation

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
        println("command type and then its help section with match pattern")
    }
    ApiCallsImplementation.getNewStories() // it works! if course it is just terrible way to test it but still

  }

}
