package commands

/**
 * common traits for all commands
 */
trait Command {
  /**
   * @param id command containment
   * @param options passed options
   * @return usually nothing, but generally used for log i guess
   */
  def execute(id : Any, options: CommandOptions) : Any

  /**
   *
   */
  def showHelp(): Unit
}
