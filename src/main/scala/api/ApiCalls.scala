package api

trait ApiCalls {
  def getUser(userId : String) : String
  def getItem(itemId : String) : String
  def getTopStories(): String
  def getBestStories(): String
  def getNewStories(): String
  def getAskStories(): String
  def getShowStories(): String
  def getJobStories(): String
  def getMaxItem(itemId : String) : String
}
