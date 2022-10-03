package api.reader
import api.objects.{ItemObject, UserObject}
import upickle.default.read

class ReaderImplementation extends ApiReader
{
// pickle will be here
  // and it should be class too
  // sealed preferably

  override def toUser(user: String): UserObject = read[UserObject](user)

  override def toItem(item: String): ItemObject = read[ItemObject](item)

  override def toStories(stories: String): Array[Int] = read[Array[Int]](stories)
}
