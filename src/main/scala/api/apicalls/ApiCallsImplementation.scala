package api.apicalls

import scala.io.Source

/**
 *
 */
class ApiCallsImplementation extends ApiCalls {
  private val ApiUrl = "https://hacker-news.firebaseio.com/v0/"

  /**
   * @param url
   * @return
   */
  private def get(url: String): String = {
    val source = Source.fromURL(url)
    val data = source.mkString

    source.close()
    data
  }

  /**
   * @param userId
   * @return
   */
  override def getUser(userId: String): String = get(ApiUrl + "user/" + userId + ".json")

  /**
   * @param itemId
   * @return
   */
  override def getItem(itemId: Int): String = get(ApiUrl + "item/" + itemId + ".json")

  /**
   * @return
   */
  override def getTopStories: String = get(ApiUrl + "topstories.json")

  /**
   * @return
   */
  override def getBestStories: String = get(ApiUrl + "beststories.json")

  /**
   * @return
   */
  override def getNewStories: String = get(ApiUrl + "newstories.json")

  /**
   * @return
   */
  override def getAskStories: String = get(ApiUrl + "askstories.json")

  /**
   * @return
   */
  override def getShowStories: String = get(ApiUrl + "showstories.json")

  /**
   * @return
   */
  override def getJobStories: String = get(ApiUrl + "jobstories.json")

  /**
   * @return
   */
  override def getMaxItem: String = get(ApiUrl + "maxitem.json")

  /**
   * @return
   */
  override def getUpdates: String = get(ApiUrl + "updates.json")
}
