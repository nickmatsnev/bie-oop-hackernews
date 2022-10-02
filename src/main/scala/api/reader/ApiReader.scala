package api.reader

import api.objects.{ItemObject, UserObject}

trait ApiReader {
  def toUser(user : String): UserObject
  def toItem(item : String): ItemObject
}
object ApiReader extends ApiReader{
  val reader : ApiReader = new ReaderImplementation()

  override def toUser(user: String): UserObject = reader.toUser(user)

  override def toItem(item: String): ItemObject = reader.toItem(item)
}

