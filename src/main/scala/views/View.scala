package views

import api.objects.{ItemObject, UserObject}

/**
 *
 */
object View {
  /**
   * @param userObject - passed user object to print
   */
  def viewUser(userObject: UserObject): Unit = Presenter.showView(ViewBuilder.buildUserView(userObject))

  /**
   * @param itemObject - passed item object to print
   */
  def viewItem(itemObject: ItemObject): Unit = Presenter.showView(ViewBuilder.buildItemView(itemObject))

  /**
   * @param commandType - shows help for a given command
   */
  def viewHelp(commandType: String): Unit = Presenter.showView(ViewBuilder.buildHelp(commandType))
}
