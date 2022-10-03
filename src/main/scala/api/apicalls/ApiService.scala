package api.apicalls

import api.objects.{ItemObject, UserObject}
import api.reader.ApiReader

class ApiService extends ApiCalls {
  override def getUser(userId: String): UserObject = {
    val user = ApiCalls.getUser(userId)
    val userObj = ApiReader.toUser(user)
    userObj
  }

  override def getItem(itemId: String): ItemObject = {
    val item = ApiCalls.getItem(itemId)
    val itemObj = ApiReader.toItem(item)
    itemObj
  }

  override def getTopStories: String = ???

  override def getBestStories: String = ???

  override def getNewStories: String = ???

  override def getAskStories: String = ???

  override def getShowStories: String = ???

  override def getJobStories: String = ???

  override def getMaxItem: String = ???
}
