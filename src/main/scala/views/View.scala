package views

import api.objects.{ItemObject, UserObject}

object View {
  def viewUser(userObject: UserObject): Unit = Presenter.showView(ViewBuilder.buildUserView(userObject))

  def viewItem(itemObject: ItemObject): Unit = Presenter.showView(ViewBuilder.buildItemView(itemObject))
}
