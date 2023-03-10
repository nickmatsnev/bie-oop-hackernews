package api.apicalls

import api.objects.{ItemObject, UpdatesObject, UserObject}
import api.reader.ApiReader
import cache.{CacheService, CacheValidator}

import java.util.Calendar

/**
 *
 */
class ApiService(val cacheService: CacheService) extends ApiCalls {
  private var passedTtl: Int = 6
  private val cacheValidator = new CacheValidator(cacheService)
  /**
   * precondition: newTTl > 0 & itemID exists, itemType is either user or item
   * post condition: none
   * @param newTtl new time to live
   * @param itemId item which was could cause revalidation
   * @param itemType path to cache
   */
  def setTtlAndValidate(newTtl: Int, itemId: String, itemType: String): Unit = {
      passedTtl = newTtl
      if (itemType == "user") {
        val optObj = ApiReader.toUser(ApiCalls.getUser(itemId))
        if (optObj.isEmpty) throw new NoSuchElementException("This user or item or command does not exist: " + itemId)
        val item = optObj.get
        if (item.created > (Calendar.getInstance().getTime.getTime - passedTtl * 1000)) cacheValidator.validateCache()
      } else {
        val optObj = ApiReader.toItem(ApiCalls.getItem(itemId.toInt))
        if (optObj.isEmpty) throw new NoSuchElementException("This user or item or command does not exist: " + itemId)
        val item = optObj.get
        if (item.time > (Calendar.getInstance().getTime.getTime - passedTtl * 1000)) cacheValidator.validateCache()
      }
  }

  /**
   * precondition: valid userID
   * post condition: returns null or UserObject
   * @param userId is string
   * @return user object by id
   */
  override def getUser(userId: String): Option[UserObject] = {
    if(cacheService.exists(userId)){
      val cachedUser = cacheService.uploadUser(userId)
      Option(cachedUser)
    }
    else{
      val userObj = ApiReader.toUser(ApiCalls.getUser(userId))
      if(userObj.isEmpty) throw new NoSuchElementException("User " + userId + " doesn't exist")
      val user = userObj.get
      cacheService.saveUser(user)
      ApiReader.toUser(ApiCalls.getUser(userId))
    }
  }

  /**
   * precondition: valid itemId
   * post condition: returns null or UserObject
   * @param itemId is int
   * @return item object by id
   */
  override def getItem(itemId: Int): Option[ItemObject] = {
    if(cacheService.exists(itemId)){
      val cachedItem = cacheService.uploadItem(itemId)
      Option(cachedItem)
    }
    else {
      val itemObj = ApiReader.toItem(ApiCalls.getItem(itemId))
      if(itemObj.isEmpty) throw new NoSuchElementException("Item " + itemId + " doesn't exist")
      val item = itemObj.get
      cacheService.saveItem(item)
      ApiReader.toItem(ApiCalls.getItem(itemId))
    }
  }

  /**
   * precondition: none
   * post condition: returns list of stories
   * @return top stories
   */
  override def getTopStories: Array[Int] = ApiReader.toStories(ApiCalls.getTopStories)

  /**
   * precondition: none
   * post condition: returns list of stories
   * @return best stories
   */
  override def getBestStories: Array[Int] = ApiReader.toStories(ApiCalls.getBestStories)

  /**
   * precondition: none
   * post condition: returns list of stories
   * @return new stories
   */
  override def getNewStories: Array[Int] = ApiReader.toStories(ApiCalls.getNewStories)

  /**
   * precondition: none
   * post condition: returns list of stories
   * @return ask stories
   */
  override def getAskStories: Array[Int] = ApiReader.toStories(ApiCalls.getAskStories)

  /**
   * precondition: none
   * post condition: returns list of stories
   * @return show stories
   */
  override def getShowStories: Array[Int] = ApiReader.toStories(ApiCalls.getShowStories)

  /**
   * precondition: none
   * post condition: returns list of stories
   * @return job stories
   */
  override def getJobStories: Array[Int] = ApiReader.toStories(ApiCalls.getJobStories)

  /**
   * precondition: none
   * post condition: returns null or UserObject
   * @return max item
   */
  override def getMaxItem: Option[ItemObject] = ApiReader.toItem(ApiCalls.getMaxItem)

  /**
   * precondition: none
   * post condition: returns two lists wrapped in my object
   * @return updates
   */
  override def getUpdates: UpdatesObject = ApiReader.toUpdates(ApiCalls.getUpdates)
}
