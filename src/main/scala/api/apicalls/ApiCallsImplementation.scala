package api.apicalls

import scala.io.Source

/**
 *
 */
class ApiCallsImplementation extends ApiCalls {
  private val ApiUrl = "https://hacker-news.firebaseio.com/v0/"

  /**
   * precondition: valid URL where we can actually retrieve data
   * post condition: returns JSON object wrapped in String class
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
   * precondition: valid userID
   * post condition: returns JSON object wrapped in String class
   * @param userId id of the user we want to get
   * @return user json
   */
  override def getUser(userId: String): String = get(ApiUrl + "user/" + userId + ".json")

  /**
   * precondition: valid itemId
   * post condition: returns JSON object wrapped in String class
   * @param itemId id of the item we want to get
   * @return item json
   */
  override def getItem(itemId: Int): String = get(ApiUrl + "item/" + itemId + ".json")

  /**
   * precondition: none
   * post condition: returns JSON object wrapped in String class
   * @return top stories ids as json
   */
  override def getTopStories: String = get(ApiUrl + "topstories.json")

  /**
   * precondition: none
   * post condition: returns JSON object wrapped in String class
   * @return best stories ids as json
   */
  override def getBestStories: String = get(ApiUrl + "beststories.json")

  /**
   * precondition: none
   * post condition: returns JSON object wrapped in String class
   * @return new stories ids as json
   */
  override def getNewStories: String = get(ApiUrl + "newstories.json")

  /**
   * precondition: none
   * post condition: returns JSON object wrapped in String class
   * @return ask stories ids as json
   */
  override def getAskStories: String = get(ApiUrl + "askstories.json")

  /**
   * precondition: none
   * post condition: returns JSON object wrapped in String class
   * @return show stories ids as json
   */
  override def getShowStories: String = get(ApiUrl + "showstories.json")

  /**
   * precondition: none
   * post condition: returns JSON object wrapped in String class
   * @return job stories ids as json
   */
  override def getJobStories: String = get(ApiUrl + "jobstories.json")

  /**
   * precondition: none
   * post condition: returns JSON object wrapped in String class
   * @return max item id as json
   */
  override def getMaxItem: String = get(ApiUrl + "maxitem.json")

  /**
   * precondition: none
   * post condition: returns JSON object wrapped in String class
   * @return top updated items and profiles ids as json
   */
  override def getUpdates: String = get(ApiUrl + "updates.json")
}