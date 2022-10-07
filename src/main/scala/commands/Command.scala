package commands

/**
 *
 */
trait Command {
  /**
   * @param id
   * @param options
   * @return
   */
  def execute(id : Any, options: CommandOptions) : Any

  /**
   *
   */
  def showHelp(): Unit
}
