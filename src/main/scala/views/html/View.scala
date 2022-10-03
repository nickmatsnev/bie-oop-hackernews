package views.html

import api.objects.{ItemObject, UserObject}

object View {
  def viewUser(userObject: UserObject): Unit = Presenter.showView(ViewBuilder.buildUserView(userObject))

  def viewStory(itemObject: ItemObject): Unit = Presenter.showView(ViewBuilder.buildStory(itemObject))
}
