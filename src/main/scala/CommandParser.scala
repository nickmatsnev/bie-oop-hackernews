object CommandParser {

  def splitCommand(passedArgs : Array[String]) : (Array[String], Array[String]) ={
    // the only -- missing element is a command itself
    // if there is --help then there can be no further args
    assert(passedArgs.length > 0)
    val commandIndex = passedArgs.indexWhere(arg => !arg.startsWith("--"))
    passedArgs.splitAt(commandIndex)
  }
  def defaultHelp(): String ={
    var help = "Welcome to the command line feed of Hacker News!\n"
    help += "Input Format: [options] [command] [command-options]\n"
    help += "Commands:\n1. --help - used for getting the manual of the program.\n"
    help += "2. --help [command] will show help regarding the specific command\n"
    help += "3. --ttl=[value] will set up the refreshment time of cache.\n"
    help += "4. --clear-cache will clear cache.\n"
    help += "5. topstories [command-options] will show the output of the topstories command\n"
    help += "6. beststories [command-options] will show the output of the beststories command\n"
    help += "7. newstories [command-options] will show the output of the newstories command\n"
    help += "8. askstories [command-options] will show the output of the askstories command\n"
    help += "9. showstories [command-options] will show the output of the showstories command\n"
    help += "10. jobstories [command-options] will show the output of the jobstories command\n"
    help += "11. [user_id] will show user info and top stories of him/her with comments\n"
    help += "12. maxitem will the most rated item on Hacker News!\n"
    help += "Command Options:\n1. --page=[value] opens the specific page of stories\n2. --showSize=[value] allows limit" +
      "ed to [value] number of rows\n3. --showTime=[valueInSeconds] shows the time each page of items will be static.\n4. --start=[k]&--end==[n] where k <= n and k is the first page and n is the last" +
      " page of selected topic.\n"
    help
  }
}
