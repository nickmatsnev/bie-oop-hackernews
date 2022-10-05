package api.apicalls

import scala.io.Source

class ApiCallsImplementation extends ApiCalls {
  private val ApiUrl = "https://hacker-news.firebaseio.com/v0/"

  private def get(url: String): String = {
    val source = Source.fromURL(url)
    val data = source.mkString

    source.close()
    data
  }

  override def getUser(userId: String): String = get(ApiUrl + "user/" + userId + ".json")

  override def getItem(itemId: Int): String = get(ApiUrl + "item/" + itemId + ".json")

  override def getTopStories: String = get(ApiUrl + "topstories.json")

  override def getBestStories: String = get(ApiUrl + "beststories.json")

  override def getNewStories: String = get(ApiUrl + "newstories.json")

  override def getAskStories: String = get(ApiUrl + "askstories.json")

  override def getShowStories: String = get(ApiUrl + "showstories.json")

  override def getJobStories: String = get(ApiUrl + "jobstories.json")

  override def getMaxItem: String = get(ApiUrl + "maxitem.json")

  override def getUpdates: String = get(ApiUrl + "updates.json")
}
