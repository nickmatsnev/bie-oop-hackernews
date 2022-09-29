package api

import scala.io.Source

object ApiCallsImplementation extends ApiCalls {
  private val ApiUrl = "https://hacker-news.firebaseio.com/v0/"

  private def get(url : String): String = {
    val source = Source.fromURL(url)
    val data = source.mkString
    print(data)
    source.close()
    data
  }

  override def getUser(userId: String): String = get(ApiUrl + "user/" + userId + ".json")

  override def getItem(itemId: String): String = get(ApiUrl + "item/" + itemId + ".json")

  override def getTopStories(): String = get(ApiUrl + "topstories.json")

  override def getBestStories(): String = get(ApiUrl + "beststories.json")

  override def getNewStories(): String = get(ApiUrl + "newstories.json")

  override def getAskStories(): String = get(ApiUrl + "askstories.json")

  override def getShowStories(): String = get(ApiUrl + "showstories.json")

  override def getJobStories(): String = get(ApiUrl + "jobstories.json")

  override def getMaxItem(itemId: String): String = get(ApiUrl + "item/" + itemId + ".json")
}
