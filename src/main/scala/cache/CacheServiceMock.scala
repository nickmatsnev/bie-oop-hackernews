package cache
import api.objects.{ItemObject, UserObject}

import scala.collection.mutable.ArrayBuffer

class CacheServiceMock(val items: ArrayBuffer[ItemObject],
                       val users: ArrayBuffer[UserObject]) extends CacheService(CacheFile) {
  /**
   * @param userObj is going to be saved to cache
   */
  override def saveUser(userObj: UserObject): Unit = {
    users += userObj
  }

  /**
   * @param itemObj is going to be saved to cache
   */
  override def saveItem(itemObj: ItemObject): Unit = {
    items += itemObj
  }
  /**
   * @param id of the needed user
   * @return uploads user from cache
   */
  override def uploadUser(id: String): UserObject = users.find(user => user.id == id).get

  /**
   * @param id of the required item
   * @return uploads item from cache
   */
  override def uploadItem(id: Int): ItemObject = items.find(item => item.id == id).get

  /**
   * clears cache
   */
  override def clearCache(): Unit = {
    users.clear()
    items.clear()
  }
}
