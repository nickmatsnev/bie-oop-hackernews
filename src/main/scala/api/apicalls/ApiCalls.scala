package api.apicalls

/**
 *
 */
trait ApiCalls {
  /**
   * @param userId id of needed user
   * @return user by id
   */
  def getUser(userId: String): Any

  /**
   * @param itemId id of needed item
   * @return item by id
   */
  def getItem(itemId: Int): Any

  /**
   * @return top stories
   */
  def getTopStories: Any

  /**
   * @return best stories
   */
  def getBestStories: Any

  /**
   * @return new stories
   */
  def getNewStories: Any

  /**
   * @return ask stories
   */
  def getAskStories: Any

  /**
   * @return show stories
   */
  def getShowStories: Any

  /**
   * @return job stories
   */
  def getJobStories: Any

  /**
   * @return max item id
   */
  def getMaxItem: Any

  /**
   * @return updates
   */
  def getUpdates: Any
}

/**
 *
 */
object ApiCalls extends ApiCalls {
  private val ApiCaller = new ApiCallsImplementation()

  /**
   * @param userId of user which would be called from api
   * @return user as string
   */
  override def getUser(userId: String): String = ApiCaller.getUser(userId)

  /**
   * @param itemId of item which would be called from api
   * @return item as string
   */
  override def getItem(itemId: Int): String = ApiCaller.getItem(itemId)

  /**
   * @return top stories
   */
  override def getTopStories: String = ApiCaller.getTopStories

  /**
   * @return best stories
   */
  override def getBestStories: String = ApiCaller.getBestStories

  /**
   * @return new stories
   */
  override def getNewStories: String = ApiCaller.getNewStories

  /**
   * @return ask stories
   */
  override def getAskStories: String = ApiCaller.getAskStories

  /**
   * @return show stories
   */
  override def getShowStories: String = ApiCaller.getShowStories

  /**
   * @return job stories
   */
  override def getJobStories: String = ApiCaller.getJobStories

  /**
   * @return max item id
   */
  override def getMaxItem: String = ApiCaller.getMaxItem

  /**
   * @return max item id
   */
  override def getUpdates: String = ApiCaller.getUpdates
}