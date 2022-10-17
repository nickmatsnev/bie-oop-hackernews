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
   * @param userId is the id of the user who would be checked
   * @return true if user exists in cache and false if not
   */
  def exists(userId: String): Boolean = CacheFile.exists(userId)

  /**
   * @param itemId is the id of the item which would be checked
   * @return true if item exists in cache and false if not
   */
  def exists(itemId: Int): Boolean = CacheFile.exists(itemId.toString)

  /**
   * @param userObj is going to be saved to cache
   */
  def saveUser(userObj : UserObject): Unit = CacheFile.add(CacheFile.toCacheObject(userObj), "user")

  /**
   * @param itemObj is going to be saved to cache
   */
  def saveItem(itemObj : ItemObject): Unit = CacheFile.add(CacheFile.toCacheObject(itemObj), "item")

  /**
   * @param id of the needed user
   * @return uploads user from cache
   */
  def uploadUser(id : String): UserObject = {
    val cacheUser = CacheFile.getCacheObject(id, "user")
    CacheFile.toUserObject(cacheUser)
  }

  /**
   * @param id of the required item
   * @return uploads item from cache
   */
  def uploadItem(id : Int): ItemObject = CacheFile.toItemObject(CacheFile.getCacheObject(id.toString, "item"))

  /**
   * clears cache
   */
  def clearCache(): Unit = CacheFile.clearCache()

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

}