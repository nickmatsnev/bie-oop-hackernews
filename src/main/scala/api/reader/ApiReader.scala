package api.reader

import api.objects.{ItemObject, UpdatesObject, UserObject}

/**
 * class for representation of api reading
 */
trait ApiReader {
  /**
   * @param user
   * @return user object or nothing
   */
  def toUser(user: String): Option[UserObject]

  /**
   * @param item
   * @return item object or nothing
   */
  def toItem(item: String): Option[ItemObject]

  /**
   * @param stories
   * @return list of stories ids
   */
  def toStories(stories: String): Array[Int]

  /**
   * @param updates
   * @return updates object
   */
  def toUpdates(updates: String): UpdatesObject
}

/**
 * used implementation
 */
object ApiReader extends ApiReader{
  val reader : ApiReader = new ReaderImplementation()

  /**
   * @param user
   * @return user object or nothing
   */
  override def toUser(user: String): Option[UserObject] = reader.toUser(user)

  /**
   * @param item
   * @return item object or nothing
   */
  override def toItem(item: String): Option[ItemObject] = reader.toItem(item)

  /**
   * @param stories
   * @return list of stories ids
   */
  override def toStories(stories: String): Array[Int] = reader.toStories(stories)

  /**
   * @param updates
   * @return updates object
   */
  override def toUpdates(updates: String): UpdatesObject = reader.toUpdates(updates)
}

