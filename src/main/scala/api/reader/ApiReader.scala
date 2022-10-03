package api.reader

import api.objects.{ItemObject, ListObject, UserObject}

trait ApiReader {
  def toUser(user : String): Option[UserObject]
  def toItem(item : String): Option[ItemObject]
  def toStories(stories : String): Array[Int]
}
object ApiReader extends ApiReader{
  val reader : ApiReader = new ReaderImplementation()

  override def toUser(user: String): Option[UserObject] = reader.toUser(user)

  override def toItem(item: String): Option[ItemObject] = reader.toItem(item)

  override def toStories(stories: String): Array[Int] = reader.toStories(stories)

}

