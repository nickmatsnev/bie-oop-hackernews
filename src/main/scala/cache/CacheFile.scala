package cache
import api.objects.{ItemObject, UserObject}
import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.control.Breaks.break
/*
* CacheObject ==
* ---
* id
* rest rest
* ---
* */
class CacheFile {
  private val cachePathItems = "cache/items/items"
  private val cachePathUsers = "cache/users/users"

  private def toLines(text: String): Array[String] = text.split("\n")

  private def getLines(itemType: String): Array[String] = {
    val path = if (itemType == "user") cachePathUsers else cachePathItems
    val source = Source.fromFile(path)
    val lines = source.getLines().toArray
    source.close()
    lines
  }

  private def existException(cacheFile: File): Unit = {
    if(!cacheFile.exists()) {
      cacheFile.createNewFile()
      throw new Exception("Cache file does not exist")
    }
  }
  def toCacheObject(itemObj : ItemObject): String = {
    var cacheObject = "---\n"
    cacheObject += itemObj.id.toString + "\n"
    cacheObject += "by " + itemObj.by + "\n"
    cacheObject += "deleted " + itemObj.deleted + "\n"
    cacheObject += "itemType " + itemObj.itemType + "\n"
    cacheObject += "time " + itemObj.time.toString + "\n"
    cacheObject += "text " + itemObj.text + "\n"
    cacheObject += "dead " + itemObj.dead.toString + "\n"
    cacheObject += "parent " + itemObj.parent.toString + "\n"
    cacheObject += "poll " + itemObj.poll.toString + "\n"
    cacheObject += "kids " + itemObj.kids.mkString("Array(", ", ", ")") + "\n"
    cacheObject += "url " + itemObj.url + "\n"
    cacheObject += "score " + itemObj.score.toString + "\n"
    cacheObject += "title " + itemObj.title + "\n"
    cacheObject += "parts " + itemObj.parts.mkString("Array(", ", ", ")") + "\n"
    cacheObject += "descendants " + itemObj.descendants.toString + "\n"
    cacheObject += "---\n"
    cacheObject
  }

  def toCacheObject(userObj : UserObject): String = {
    var cacheObject = "---\n"
    cacheObject += " " + userObj.id + "\n"
    cacheObject += "created " + userObj.created.toString + "\n"
    cacheObject += "karma " + userObj.karma.toString + "\n"
    cacheObject += "about " + userObj.about + "\n"
    cacheObject += "submitted " + userObj.submitted.mkString("Array(", ", ", ")") + "\n"
    cacheObject += "---\n"
    cacheObject
  }

  def toItemObject(cacheObj: String): ItemObject = {

    var id : Int = -1
    var deleted : Boolean = false
    var itemType : String = "Unknown type"
    var by : String = "Unknown Author"
    var time : Long = 0
    var text : String = "No text"
    var dead : Boolean = false
    var parent : Int = -1
    var poll : Int = -1
    var kids : Array[Int] = Array()
    var url : String = "No url"
    var score : Int = -1
    var title : String = "No title"
    var parts : Array[Int] = Array()
    var descendants: Int = -1

    var lines = toLines(cacheObj).tail

    id = lines.head.asInstanceOf[Int]
    lines = lines.drop(1)

    if (lines.head.startsWith("by")) {
      by = lines.head.drop(3)
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("deleted")){
      deleted = lines.head.drop(8).toBoolean
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("itemType")){
      itemType = lines.head.drop(9)
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("time")) {
      time = lines.head.drop(5).toLong
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("text")) {
      text = lines.head.drop(5)
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("dead")) {
      dead = lines.head.drop(5).toBoolean
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("parent")) {
      parent = lines.head.drop(7).toInt
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("poll")) {
      poll = lines.head.drop(5).toInt
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("kids")) {
      kids = lines.head.drop(5).to(Array[Int])
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("url")) {
      url = lines.head.drop(4)
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("score")) {
      score = lines.head.drop(6).toInt
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("title")) {
      title = lines.head.drop(6)
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("parts")) {
      parts = lines.head.drop(6).to(Array[Int])
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("descendants")) {
      descendants = lines.head.drop(12).toInt
      lines = lines.drop(1)
    }
    ItemObject(id = id,
      by = by,
      deleted = deleted,
      itemType = itemType,
      time = time,
      title = title,
      text = text,
      score = score,
      kids = kids,
      parts = parts,
      descendants = descendants,
      poll = poll,
      parent = parent,
      dead = dead,
      url = url
    )
  }

  def toUserObject(cacheObj: String): UserObject = {
    var id : String = "No name"
    var created : Long = 0
    var karma : Int = 0
    var about : String = "No description"
    var submitted : Array[Int] = Array()

    var lines = toLines(cacheObj).tail

    id = lines.head
    lines = lines.drop(1)

    if (lines.head.startsWith("created")){
      created = lines.head.take(8).toLong
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("karma")){
      karma = lines.head.take(6).toInt
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("about")){
      about = lines.head.take(6)
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("submitted")){
      submitted = lines.head.take(10).to(Array[Int])
      lines = lines.drop(1)
    }
    UserObject(
      id = id,
      created = created,
      karma = karma,
      about = about,
      submitted = submitted
    )
  }


  def getCacheObject(itemId : String, itemType: String) : String = {
    var cacheObj = "---\n" + itemId + "\n"
    itemType match {
      case "user" => getCacheObj(cachePathUsers, 4)
      case _ => getCacheObj(cachePathItems, 15)
    }
    cacheObj += "---\n"
    def getCacheObj(path: String, counterLimit: Int): Unit = {
      val cacheFile = new File(path)
      existException(cacheFile)
      val cacheLines = getLines("item")
      var found: Boolean = false
      var counter: Int = 0
      for (line <- cacheLines){
        if (counter > counterLimit || line.startsWith("---")) break()
        if (found) {
          counter += 1
          cacheObj += line
        }
        if (line.startsWith(itemId)) found = true
      }
    }
    cacheObj
  }


  def replace(itemId : String, itemType: String, newCacheObject : String) : Unit = {
    val path: String = if (itemType == "user") cachePathUsers else cachePathItems
    val counterLimit: Int = if(itemType == "user") 4 else 15
    val cacheFile = new File(path)
    val newCacheFile = new File(path)
    existException(cacheFile)
    val writer = new FileWriter(newCacheFile, true)
    val cacheLines = getLines(itemType)
    var found: Boolean = false
    var counter: Int = 0
    for (line <- cacheLines){
      writer.append(line)
      if (counter > counterLimit) found = false
      if (found) {
        counter += 1
        writer.append(newCacheObject)
      }
      if (line.startsWith(itemId)) found = true
    }
    writer.close()
    newCacheFile.renameTo(cacheFile)
  }


  def getAll: Array[String] = {
    val allObjects = Array(String)
    val userObjects = getLines("user")
    val itemObjects = getLines("item")
    val allObjectsString = userObjects.concat(itemObjects).mkString("", "", "")
    val allRaw = allObjectsString.split("---\n---")
    // since we split it by ---\n---, the first element has --- on top and the last element has --- at the bottom
    val firstClean = allRaw.head.take(3)
    val lastClean = allRaw.reverse.head.take(3)
    val middleClean = allRaw.take(1).takeRight(1)
    allObjects.concat(firstClean)
    allObjects.concat(middleClean.concat(lastClean))
    allObjects
  }

  /*
  * CacheObject ==
  * ---
  * id
  * the rest
  * ---
  * */
  def add(cacheObject : String, typeOfObj : String) : Unit =
    {
      typeOfObj match {
        case "user" => write(cachePathUsers, cacheObject)
        case "item" => write(cachePathItems, cacheObject)
      }
    }

  def write(path : String, cacheObject : String): Unit = {
    val cacheFile = new File(path)
    if(!cacheFile.exists()) cacheFile.createNewFile()
    val lines = toLines(cacheObject)
    val writer = new FileWriter(cacheFile, true)
    writer.append("---\n")
    for (line <- lines) {
      writer.append(line + '\n')
    }
    writer.append("---\n")
    writer.close()
  }

  def clearCache : Unit = {
    val writerItems = new FileWriter(cachePathItems)
    val writerUsers = new FileWriter(cachePathUsers)
    writerItems.append("")
    writerUsers.append("")
    writerItems.close
    writerUsers.close
  }
}

object CacheFile extends CacheFile {

  override def toCacheObject(itemObj: ItemObject): String = super.toCacheObject(itemObj)

  override def toCacheObject(userObj: UserObject): String = super.toCacheObject(userObj)

  override def toItemObject(cacheObj: String): ItemObject = super.toItemObject(cacheObj)

  override def toUserObject(cacheObj: String): UserObject = super.toUserObject(cacheObj)

  override def getCacheObject(itemId: String, itemType: String): String = super.getCacheObject(itemId, itemType)

  override def replace(itemId: String, itemType: String, newCacheObject: String): Unit = super.replace(itemId, itemType, newCacheObject)

  override def getAll: Array[String] = super.getAll

  override def add(cacheObject: String, typeOfObj: String): Unit = super.add(cacheObject, typeOfObj)

  override def write(path: String, cacheObject: String): Unit = super.write(path, cacheObject)

  override def clearCache: Unit = super.clearCache
}