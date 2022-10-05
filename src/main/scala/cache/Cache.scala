package cache

import api.objects.{ItemObject, UserObject}
import cache.CacheFile
import api.apicalls.ApiService

class Cache {
  private var ttl = 600

  def setTtl(newTtl: Int) : Unit = ttl = newTtl

  def saveUser(userObj : UserObject): Unit = CacheFile.add(CacheFile.toCacheObject(userObj), "user")

  def saveItem(itemObj : UserObject): Unit = CacheFile.add(CacheFile.toCacheObject(itemObj), "item")

  def uploadUser(id : String): UserObject = CacheFile.toUserObject(CacheFile.getCacheObject(id, "user"))

  def uploadItem(id : Int): ItemObject = CacheFile.toItemObject(CacheFile.getCacheObject(id.toString, "item"))

  def clearCache = CacheFile.clearCache

  def validateCache: Unit = {
    val apiService = new ApiService()
    val updates = apiService.getUpdates
    // get updates for users
    // get updates for items
    // update them
    var itemObjects = Array()
    val userObjects = Array()
    val cachedItems = CacheFile.getAll

    for(itemId <- updates.items){
      val itemObj = apiService.getItem(itemId)
      if(itemObj.isEmpty) throw new NoSuchElementException("Item " + itemId.toString + " doesn't exist")
      val item = itemObj.get
      val updatedItem = CacheFile.toCacheObject(item)
      for (cachedItem <- cachedItems){
        if(cachedItem.split("\n").take(1).head == item.id.toString)
          CacheFile.replace(item.id.toString, "item", updatedItem)
      }
    }
  }
}

object Cache extends Cache {
  override def setTtl(newTtl: Int): Unit = super.setTtl(newTtl)

  override def saveUser(userObj: UserObject): Unit = super.saveUser(userObj)

  override def saveItem(itemObj: UserObject): Unit = super.saveItem(itemObj)

  override def uploadUser(id: String): UserObject = super.uploadUser(id)

  override def uploadItem(id: Int): ItemObject = super.uploadItem(id)

  override def clearCache: Unit = super.clearCache

  override def validateCache: Unit = super.validateCache
}