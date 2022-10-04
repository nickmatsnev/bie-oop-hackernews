import commands.{StoriesCommand, UserCommand}

class CommandFactory {
  def create(commandName: String): Unit = {
    commandName match {
      case "newstories" | "beststories" | "showstories" | "askstories" | "jobstories" | "topstories" =>
        StoriesCommand.execute(commandName)
      case _ => UserCommand.execute(commandName)
    }
  }
}
