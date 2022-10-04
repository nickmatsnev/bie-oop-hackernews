package commands

trait Command {
  def execute(id : Any) : Any
  def showHelp()
}
