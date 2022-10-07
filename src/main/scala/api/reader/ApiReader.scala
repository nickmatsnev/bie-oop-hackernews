package api.reader

import api.objects.{ItemObject, UpdatesObject, UserObject}

/**
 *
 */
trait ApiReader {
  /**
   * @param user
   * @return
   */
  def toUser(user: String): Option[UserObject]

  /**
   * @param item
   * @return
   */
  def toItem(item: String): Option[ItemObject]

  /**
   * @param stories
   * @return
   */
  def toStories(stories: String): Array[Int]

  /**
   * @param updates
   * @return
   */
  def toUpdates(updates: String): UpdatesObject
}

/**
 *
 */
object ApiReader extends ApiReader{
  val reader : ApiReader = new ReaderImplementation()

  /**
   * @param user
   * @return
   */
  override def toUser(user: String): Option[UserObject] = reader.toUser(user)

  /**
   * @param item
   * @return
   */
  override def toItem(item: String): Option[ItemObject] = reader.toItem(item)

  /**
   * @param stories
   * @return
   */
  override def toStories(stories: String): Array[Int] = reader.toStories(stories)

  /**
   * @param updates
   * @return
   */
  override def toUpdates(updates: String): UpdatesObject = reader.toUpdates(updates)
}

