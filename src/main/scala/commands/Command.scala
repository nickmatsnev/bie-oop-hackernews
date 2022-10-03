package commands

trait Command {
  def execute(id: Any): Unit
}
