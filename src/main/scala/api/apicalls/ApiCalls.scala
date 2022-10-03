package api.apicalls

trait ApiCalls {
  def getUser(userId: String): Any

  def getItem(itemId: Int): Any

  def getTopStories: Any

  def getBestStories: Any

  def getNewStories: Any

  def getAskStories: Any

  def getShowStories: Any

  def getJobStories: Any

  def getMaxItem: Any
}

object ApiCalls extends ApiCalls {
  private val ApiCaller = new ApiCallsImplementation()

  override def getUser(userId: String): String = ApiCaller.getUser(userId)

  override def getItem(itemId: Int): String = ApiCaller.getItem(itemId)

  override def getTopStories: String = ApiCaller.getTopStories

  override def getBestStories: String = ApiCaller.getBestStories

  override def getNewStories: String = ApiCaller.getNewStories

  override def getAskStories: String = ApiCaller.getAskStories

  override def getShowStories: String = ApiCaller.getShowStories

  override def getJobStories: String = ApiCaller.getJobStories

  override def getMaxItem: String = ApiCaller.getMaxItem
}