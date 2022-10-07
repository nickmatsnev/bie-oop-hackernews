package api.apicalls

import api.objects.{ItemObject, UpdatesObject, UserObject}
import api.reader.ApiReader
import cache.CacheService

import java.util.Calendar

class ApiService extends ApiCalls {
  private val cache = new CacheService()

  private var passedTtl: Int = 600

  def setTtl(newTtl: Int, itemId: String, itemType: String): Unit = {
      passedTtl = newTtl
      if (itemType == "user") {
        val optObj = ApiReader.toUser(ApiCalls.getUser(itemId))
        if (optObj.isEmpty) throw new NoSuchElementException("TTL set up failed because of this user: " + itemId)
        val item = optObj.get
        if (item.created > (Calendar.getInstance().getTime.getTime - passedTtl * 1000)) cache.validateCache()
      } else {
        val optObj = ApiReader.toItem(ApiCalls.getItem(itemId.toInt))
        if (optObj.isEmpty) throw new NoSuchElementException("TTL set up failed because of this item: " + itemId)
        val item = optObj.get
        if (item.time > (Calendar.getInstance().getTime.getTime - passedTtl * 1000)) cache.validateCache()
      }
  }

  override def getUser(userId: String): Option[UserObject] = {
    if(cache.exists(userId)){
      val cachedUser = cache.uploadUser(userId)
      Option(cachedUser)
    }
    else{
      val userObj = ApiReader.toUser(ApiCalls.getUser(userId))
      if(userObj.isEmpty) throw new NoSuchElementException("User " + userId + " doesn't exist")
      val user = userObj.get
      cache.saveUser(user)
      ApiReader.toUser(ApiCalls.getUser(userId))
    }
  }

  override def getItem(itemId: Int): Option[ItemObject] = {
    if(cache.exists(itemId)){
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
