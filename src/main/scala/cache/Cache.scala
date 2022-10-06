package cache

import api.objects.{ItemObject, UserObject}
import cache.CacheFile
import api.apicalls.ApiService

class Cache {
  private var ttl = 600

  def setTtl(newTtl: Int) : Unit = ttl = newTtl

  def exists(userId: String) = CacheFile.exists(userId)

  def exists(itemId: Int) = CacheFile.exists(itemId.toString)

  def saveUser(userObj : UserObject): Unit = CacheFile.add(CacheFile.toCacheObject(userObj), "user")

  def saveItem(itemObj : ItemObject): Unit = CacheFile.add(CacheFile.toCacheObject(itemObj), "item")

  def uploadUser(id : String): UserObject = {
    val cacheUser = CacheFile.getCacheObject(id, "user")
    CacheFile.toUserObject(cacheUser)
  }

  def uploadItem(id : Int): ItemObject = CacheFile.toItemObject(CacheFile.getCacheObject(id.toString, "item"))

  def clearCache = CacheFile.clearCache

  def validateCache: Unit = {
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
      if(userObj.isEmpty) throw new NoSuchElementException("User " + userId.toString + " doesn't exist")
      val user = userObj.get
      val updatedUser = CacheFile.toCacheObject(user)
      for (cachedUser <- cachedObjects){
        if(cachedUser.split("\n").take(1).head == user.id)
          CacheFile.replace(user.id, "user", cachedUser)
      }
    }
  }
}

object Cache extends Cache {
  override def setTtl(newTtl: Int): Unit = super.setTtl(newTtl)

  override def saveUser(userObj: UserObject): Unit = super.saveUser(userObj)

  override def saveItem(itemObj: ItemObject): Unit = super.saveItem(itemObj)

  override def uploadUser(id: String): UserObject = super.uploadUser(id)

  override def uploadItem(id: Int): ItemObject = super.uploadItem(id)

  override def clearCache: Unit = super.clearCache

  override def validateCache: Unit = super.validateCache
}