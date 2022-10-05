package api.reader
import api.objects.{ItemObject, UpdatesObject, UserObject}
import upickle.default.read

class ReaderImplementation extends ApiReader
{
// pickle will be here
  // and it should be class too
  // sealed preferably

  override def toUser(user: String): Option[UserObject] = Option(read[UserObject](user))

  override def toItem(item: String): Option[ItemObject] = Option(read[ItemObject](item))

  override def toStories(stories: String): Array[Int] = read[Array[Int]](stories)

  override def toUpdates(updates: String): UpdatesObject = read[UpdatesObject](updates)
}
