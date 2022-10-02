package api.reader
import api.objects.{ItemObject, UserObject}
import upickle.default.read

class ReaderImplementation extends ApiReader
{
// pickle will be here
  // and it should be class too
  // sealed preferably

  def toUser(user: String): UserObject = read[UserObject](user)

  def toItem(item: String): ItemObject = read[ItemObject](item)

}
