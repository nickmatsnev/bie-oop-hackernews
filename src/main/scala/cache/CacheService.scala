package cache

import api.objects.{ItemObject, UserObject}
import cache.CacheFile
import api.apicalls.ApiService

/**
 * used for operations with cache from the business layer
 */
class CacheService {
  private var ttl = 600

  /**
   * @param newTtl sets ttl different from the default
   */
  def setTtl(newTtl: Int) : Unit = ttl = newTtl

  /**
   * @param userId
   * @return true if user exists in cache and false if not
   */
  def exists(userId: String): Boolean = CacheFile.exists(userId)

  /**
   * @param itemId
   * @return true if item exists in cache and false if not
   */
  def exists(itemId: Int): Boolean = CacheFile.exists(itemId.toString)

  /**
   * @param userObj is going to be saved to cache
   */
  def saveUser(userObj : UserObject): Unit = CacheFile.add(CacheFile.toCacheObject(userObj), "user")

  /**
   * @param itemObj is goung to be saved to cache
   */
  def saveItem(itemObj : ItemObject): Unit = CacheFile.add(CacheFile.toCacheObject(itemObj), "item")

  /**
   * @param id
   * @return uploads user from cache
   */
  def uploadUser(id : String): UserObject = {
    val cacheUser = CacheFile.getCacheObject(id, "user")
    CacheFile.toUserObject(cacheUser)
  }

  /**
   * @param id
   * @return uploads item from cache
   */
  def uploadItem(id : Int): ItemObject = CacheFile.toItemObject(CacheFile.getCacheObject(id.toString, "item"))

  /**
   * clears cache
   */
  def clearCache(): Unit = CacheFile.clearCache()

  /**
   * validates cache
   */
  def validateCache(): Unit = {
    val apiService = new ApiService()
    val updates = apiService.getUpdates
    // get updates for users
    // get updates for items
    // update them
    val cachedObjects = CacheFile.getAll

    for(itemId <- updates.items){
      val itemObj = apiService.getItem(itemId)
      if(itemObj.isEmpty) throw new NoSuchElementException("Item " + itemId.toString + " doesn't exist")
      val item = itemObj.get
      val updatedItem = CacheFile.toCacheObject(item)
      for (cachedItem <- cachedObjects){
        if(cachedItem.split("\n").take(1).head == item.id.toString)
          CacheFile.replace(item.id.toString, "item", updatedItem)
      }
    }
    for(userId <- updates.profiles){
      val userObj = apiService.getUser(userId)
      if(userObj.isEmpty) throw new NoSuchElementException("User " + userId + " doesn't exist.")
      val user = userObj.get
      val updatedUser = CacheFile.toCacheObject(user)
      for (cachedUser <- cachedObjects){
        if(cachedUser.split("\n").take(1).head == user.id)
          CacheFile.replace(user.id, "user", updatedUser)
      }
    }
  }
}

/**
 *
 */
object CacheService extends CacheService {
  /**
   * @param newTtl
   */
  override def setTtl(newTtl: Int): Unit = super.setTtl(newTtl)

  /**
   * @param userObj
   */
  override def saveUser(userObj: UserObject): Unit = super.saveUser(userObj)

  /**
   * @param itemObj
   */
  override def saveItem(itemObj: ItemObject): Unit = super.saveItem(itemObj)

  /**
   * @param id
   * @return
   */
  override def uploadUser(id: String): UserObject = super.uploadUser(id)

  /**
   * @param id
   * @return
   */
  override def uploadItem(id: Int): ItemObject = super.uploadItem(id)

  /**
   *
   */
  override def clearCache(): Unit = super.clearCache()

  /**
   *
   */
  override def validateCache(): Unit = super.validateCache()
}