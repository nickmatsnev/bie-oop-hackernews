package cache

import api.apicalls.ApiService

class CacheValidator(val cacheService: CacheService) {
  /**
   * validates cache
   */
  def validateCache(): Unit = {
    val apiService = new ApiService(cacheService)
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
