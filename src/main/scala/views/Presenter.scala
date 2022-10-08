package views

/**
 * Singleton
 */
object Presenter {
  /**
   * @param view - string to output to the command line
   */
  def showView(view: String): Unit = println(view)
}
