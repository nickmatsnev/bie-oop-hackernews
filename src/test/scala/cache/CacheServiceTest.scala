package cache

import api.objects.{ItemObject, UserObject}
import org.scalatest.funsuite.AnyFunSuite
import views.ViewBuilder

class CacheServiceTest extends AnyFunSuite {
  test("test existing user uploadUser get"){
    val probeUser: UserObject = UserObject(
      id = "test", created = 1, karma = 2,
      about = "descriptionAbout",
      submitted = Array(1,2,3,4)
    )
    val realUser: UserObject = CacheService.uploadUser("test")
    assert(realUser.id == probeUser.id &&
          realUser.karma == probeUser.karma &&
          realUser.about == probeUser.about &&
          realUser.created == probeUser.created)
  }
  test("test existing item uploadUser"){
      val probeItem: ItemObject = ItemObject(
        id = 101010,
        itemType = "story",
        by = "testerNick",
        time = 1665156530,
        url = "nick.matsnev",
        score = 7122,
        title = "Please give me an A",
        descendants = 49)
      val realItem: ItemObject = CacheService.uploadItem(101010)
      assert(realItem.id == probeItem.id &&
        realItem.itemType == probeItem.itemType &&
        realItem.by == probeItem.by &&
        realItem.time == probeItem.time &&
        realItem.url == probeItem.url &&
        realItem.score == probeItem.score &&
        realItem.descendants == probeItem.descendants &&
        realItem.title == probeItem.title)
  }
}
