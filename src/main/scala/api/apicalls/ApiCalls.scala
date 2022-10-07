package api.apicalls

/**
 *
 */
trait ApiCalls {
  /**
   * @param userId
   * @return
   */
  def getUser(userId: String): Any

  /**
   * @param itemId
   * @return
   */
  def getItem(itemId: Int): Any

  /**
   * @return
   */
  def getTopStories: Any

  /**
   * @return
   */
  def getBestStories: Any

  /**
   * @return
   */
  def getNewStories: Any

  /**
   * @return
   */
  def getAskStories: Any

  /**
   * @return
   */
  def getShowStories: Any

  /**
   * @return
   */
  def getJobStories: Any

  /**
   * @return
   */
  def getMaxItem: Any

  /**
   * @return
   */
  def getUpdates: Any
}

/**
 *
 */
object ApiCalls extends ApiCalls {
  private val ApiCaller = new ApiCallsImplementation()

  /**
   * @param userId
   * @return
   */
  override def getUser(userId: String): String = ApiCaller.getUser(userId)

  /**
   * @param itemId
   * @return
   */
  override def getItem(itemId: Int): String = ApiCaller.getItem(itemId)

  /**
   * @return
   */
  override def getTopStories: String = ApiCaller.getTopStories

  /**
   * @return
   */
  override def getBestStories: String = ApiCaller.getBestStories

  /**
   * @return
   */
  override def getNewStories: String = ApiCaller.getNewStories

  /**
   * @return
   */
  override def getAskStories: String = ApiCaller.getAskStories

  /**
   * @return
   */
  override def getShowStories: String = ApiCaller.getShowStories

  /**
   * @return
   */
  override def getJobStories: String = ApiCaller.getJobStories

  /**
   * @return
   */
  override def getMaxItem: String = ApiCaller.getMaxItem

  /**
   * @return
   */
  override def getUpdates: String = ApiCaller.getUpdates
}