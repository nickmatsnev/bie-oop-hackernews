package cache

import api.objects.UserObject

class Cache {
  private var ttl = 600

  def setTtl(newTtl: Int) : Unit = ttl = newTtl

  def saveUser(userObj : UserObject): Unit = ???

  def saveItem(userObj : UserObject): Unit = ???

  def uploadUser(id : String): Unit = ???

  def uploadItem(id : Int): Unit = ???

  def clearCache = ???

  def validateCache: Unit = ???
}
