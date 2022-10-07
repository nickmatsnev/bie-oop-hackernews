package views

import api.objects.{ItemObject, UserObject}

/**
 *
 */
object View {
  /**
   * @param userObject
   */
  def viewUser(userObject: UserObject): Unit = Presenter.showView(ViewBuilder.buildUserView(userObject))

  /**
   * @param itemObject
   */
  def viewItem(itemObject: ItemObject): Unit = Presenter.showView(ViewBuilder.buildItemView(itemObject))

  /**
   * @param commandType
   */
  def viewHelp(commandType: String): Unit = Presenter.showView(ViewBuilder.buildHelp(commandType))
}
