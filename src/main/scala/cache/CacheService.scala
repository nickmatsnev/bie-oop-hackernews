package cache

import api.objects.{ItemObject, UserObject}

/**
 * used for operations with cache from the business layer
 */
class CacheService(val cacheFile: CacheFile) {
  private var ttl = 600
  /**
   * @param newTtl sets ttl different from the default
   */
  def setTtl(newTtl: Int) : Unit = ttl = newTtl

  /**
   * @param userId is the id of the user who would be checked
   * @return true if user exists in cache and false if not
   */
  def exists(userId: String): Boolean = cacheFile.exists(userId)

  /**
   * @param itemId is the id of the item which would be checked
   * @return true if item exists in cache and false if not
   */
  def exists(itemId: Int): Boolean = cacheFile.exists(itemId.toString)

  /**
   * @param userObj is going to be saved to cache
   */
  def saveUser(userObj : UserObject): Unit = cacheFile.add(cacheFile.toCacheObject(userObj), "user")

  /**
   * @param itemObj is going to be saved to cache
   */
  def saveItem(itemObj : ItemObject): Unit = cacheFile.add(cacheFile.toCacheObject(itemObj), "item")

  /**
   * @param id of the needed user
   * @return uploads user from cache
   */
  def uploadUser(id : String): UserObject = {
    val cacheUser = cacheFile.getCacheObject(id, "user")
    cacheFile.toUserObject(cacheUser)
  }

  /**
   * @param id of the required item
   * @return uploads item from cache
   */
  def uploadItem(id : Int): ItemObject = cacheFile.toItemObject(cacheFile.getCacheObject(id.toString, "item"))

  /**
   * clears cache
   */
  def clearCache(): Unit = cacheFile.clearCache()

}