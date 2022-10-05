package api.apicalls

import api.objects.{ItemObject, UpdatesObject, UserObject}
import api.reader.ApiReader
import cache.Cache

class ApiService extends ApiCalls {
  private val cache = new Cache()
  override def getUser(userId: String): Option[UserObject] = {
    val cachedUser = cache.uploadUser(userId)
    if( cachedUser.id == "No name")
      ApiReader.toUser(ApiCalls.getUser(userId))
    else
      Option(cachedUser)
  }

  override def getItem(itemId: Int): Option[ItemObject] = {
    val cachedItem = cache.uploadItem(itemId)
    if (cachedItem.id.toString == "No name")
      ApiReader.toItem(ApiCalls.getItem(itemId))
    else
      Option(cachedItem)
  }

  override def getTopStories: Array[Int] = ApiReader.toStories(ApiCalls.getTopStories)

  override def getBestStories: Array[Int] = ApiReader.toStories(ApiCalls.getBestStories)

  override def getNewStories: Array[Int] = ApiReader.toStories(ApiCalls.getNewStories)

  override def getAskStories: Array[Int] = ApiReader.toStories(ApiCalls.getAskStories)

  override def getShowStories: Array[Int] = ApiReader.toStories(ApiCalls.getShowStories)

  override def getJobStories: Array[Int] = ApiReader.toStories(ApiCalls.getJobStories)

  override def getMaxItem: Option[ItemObject] = ApiReader.toItem(ApiCalls.getMaxItem)

  override def getUpdates: UpdatesObject = ApiReader.toUpdates(ApiCalls.getUpdates)
}
