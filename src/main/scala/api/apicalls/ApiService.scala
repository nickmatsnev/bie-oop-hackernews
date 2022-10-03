package api.apicalls

import api.objects.{ItemObject, UserObject}
import api.reader.ApiReader

class ApiService extends ApiCalls {

  override def getUser(userId: String): UserObject = ApiReader.toUser(ApiCalls.getUser(userId))

  override def getItem(itemId: Int): ItemObject = ApiReader.toItem(ApiCalls.getItem(itemId))

  override def getTopStories: Array[Int] = ApiReader.toStories(ApiCalls.getTopStories)

  override def getBestStories: Array[Int] = ApiReader.toStories(ApiCalls.getBestStories)

  override def getNewStories: Array[Int] = ApiReader.toStories(ApiCalls.getNewStories)

  override def getAskStories: Array[Int] = ApiReader.toStories(ApiCalls.getAskStories)

  override def getShowStories: Array[Int] = ApiReader.toStories(ApiCalls.getShowStories)

  override def getJobStories: Array[Int] = ApiReader.toStories(ApiCalls.getJobStories)

  override def getMaxItem: ItemObject = ApiReader.toItem(ApiCalls.getMaxItem)
}
