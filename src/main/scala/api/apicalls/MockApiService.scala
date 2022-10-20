package api.apicalls

import api.objects.{ItemObject, UserObject}
import cache.CacheService

class MockApiService(override val cacheService: CacheService, val users: Array[UserObject], val topStories: Array[Int], val bestStories: Array[Int]) extends ApiService(cacheService) {
  /**
   * @param userId is string
   * @return user object by id
   */
  override def getUser(userId: String): Option[UserObject] = users.find(user => user.id == userId)

  /**
   * @return top stories
   */
  override def getTopStories: Array[Int] = topStories

  override def getBestStories: Array[Int] = bestStories
}
