package api.apicalls

import scala.io.Source

/**
 *
 */
class ApiCallsImplementation extends ApiCalls {
  private val ApiUrl = "https://hacker-news.firebaseio.com/v0/"

  /**
   * @param url where to get json
   * @return json body
   */
  private def get(url: String): String = {
    val source = Source.fromURL(url)
    val data = source.mkString

    source.close()
    data
  }

  /**
   * @param userId
   * @return user json
   */
  override def getUser(userId: String): String = get(ApiUrl + "user/" + userId + ".json")

  /**
   * @param itemId
   * @return item json
   */
  override def getItem(itemId: Int): String = get(ApiUrl + "item/" + itemId + ".json")

  /**
   * @return top stories ids as json
   */
  override def getTopStories: String = get(ApiUrl + "topstories.json")

  /**
   * @return best stories ids as json
   */
  override def getBestStories: String = get(ApiUrl + "beststories.json")

  /**
   * @return new stories ids as json
   */
  override def getNewStories: String = get(ApiUrl + "newstories.json")

  /**
   * @return ask stories ids as json
   */
  override def getAskStories: String = get(ApiUrl + "askstories.json")

  /**
   * @return show stories ids as json
   */
  override def getShowStories: String = get(ApiUrl + "showstories.json")

  /**
   * @return job stories ids as json
   */
  override def getJobStories: String = get(ApiUrl + "jobstories.json")

  /**
   * @return max item id as json
   */
  override def getMaxItem: String = get(ApiUrl + "maxitem.json")

  /**
   * @return top updated items and profiles ids as json
   */
  override def getUpdates: String = get(ApiUrl + "updates.json")
}
