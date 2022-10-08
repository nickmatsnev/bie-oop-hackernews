package api.reader
import api.objects.{ItemObject, UpdatesObject, UserObject}
import upickle.default.read

/**
 *
 */
class ReaderImplementation extends ApiReader
{
  /**
   * @param user
   * @return
   */
  override def toUser(user: String): Option[UserObject] = Option(read[UserObject](user))

  /**
   * @param item
   * @return
   */
  override def toItem(item: String): Option[ItemObject] = Option(read[ItemObject](item))

  /**
   * @param stories
   * @return
   */
  override def toStories(stories: String): Array[Int] = read[Array[Int]](stories)

  /**
   * @param updates
   * @return
   */
  override def toUpdates(updates: String): UpdatesObject = read[UpdatesObject](updates)
}
