package views.html

import api.objects.UserObject

object View {
  def viewUser(userObject: UserObject): Unit = Presenter.showView(ViewBuilder.buildUserView(userObject))
}
