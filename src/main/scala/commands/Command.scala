package commands

trait Command {
  def execute(id : Any, options: CommandOptions) : Any
  def showHelp(): Unit
}
