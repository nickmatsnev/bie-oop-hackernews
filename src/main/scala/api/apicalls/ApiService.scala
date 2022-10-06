package api.apicalls

import api.objects.{ItemObject, UpdatesObject, UserObject}
import api.reader.ApiReader
import cache.{Cache, CacheFile}

class ApiService extends ApiCalls {
  private val cache = new Cache()

  private var passedTtl: Int = -1

  def setTtl(newTtl: Int): Unit = if (newTtl > 0) passedTtl = newTtl

  override def getUser(userId: String): Option[UserObject] = {
    if (passedTtl != -1) cache.validateCache
    val cachedUser = cache.uploadUser(userId)
    if( cachedUser.created == 0 && !cache.exists(userId)) {
      val userObj = ApiReader.toUser(ApiCalls.getUser(userId))
      if(userObj.isEmpty) throw new NoSuchElementException("User " + userId + " doesn't exist")
      val user = userObj.get
      cache.saveUser(user)
      ApiReader.toUser(ApiCalls.getUser(userId))
    } else
      Option(cachedUser)
  }

  override def getItem(itemId: Int): Option[ItemObject] = {
    if(cache.exists(itemId)){
      if (passedTtl != -1) cache.validateCache
      val cachedItem = cache.uploadItem(itemId)
      Option(cachedItem)
    }
    else {
      val itemObj = ApiReader.toItem(ApiCalls.getItem(itemId))
      if(itemObj.isEmpty) throw new NoSuchElementException("Item " + itemId + " doesn't exist")
      val item = itemObj.get
      cache.saveItem(item)
      ApiReader.toItem(ApiCalls.getItem(itemId))
    }
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
