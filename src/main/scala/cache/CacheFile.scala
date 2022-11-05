package cache
import api.objects.{ItemObject, UserObject}
import java.io.{File, FileWriter}
import scala.io.Source

/**
 * Class used for operations with cache from the file point of view
 */
/*
* CacheObject ==
* ---
* id
* rest rest
* */
class CacheFile(val cachePathItems: String, val cachePathUsers: String) {


  /**
   * precondition: none
   * post condition: returns an array of strings
   * @param text - given text
   * @return lines of text in array
   */
  private def toLines(text: String): Array[String] = text.split("\n")

  /**
   * precondition: string object which represents an array of strings
   * post condition: returns an array of ints
   * @param text - given text
   * @return array of integers from string representation from cache
   */
  // text should be (id,id,id,...,id) ; we take first&last elems from string as they're () and then split and convert
  private def toIntArray(text: String): Array[Int] =
    if (text.dropRight(1) != "") text.dropRight(1).split(",").map(_.toInt)
    else Array()

  /**
   * precondition: correct file path
   * post condition: returns value of file in Scala format
   * @param path - given path to file
   * @return content of file in string
   */
  private def getFileAsString(path: String): String = {
    val source = Source.fromFile(path)
    val lines = source.mkString
    source.close()
    lines
  }

  /**
   * precondition: correct item type
   * post condition: returns value of file in Scala list format
   * @param itemType - given item type
   * @return string of user or item cache
   */
  private def getLines(itemType: String): Array[String] = {
    val path = if (itemType == "user") cachePathUsers else cachePathItems
    getFileAsString(path).split('\n')
  }

  /**
   * @param cacheFile - cache file descriptor
   */
  private def existException(cacheFile: File): Unit = {
    if(!cacheFile.exists()) {
      throw new Exception("The cache file at " + cacheFile.getAbsolutePath + " or at " + cacheFile.getCanonicalPath + " is not found.")
    }
  }

  /**
   * precondition: give an item object
   * post condition: returns value of an item object in String
   * @param itemObj - passed object
   * @return string from item
   */
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
    cacheObject += "kids " + itemObj.kids.mkString("Array(", ",", ")") + "\n"
    cacheObject += "url " + itemObj.url + "\n"
    cacheObject += "score " + itemObj.score.toString + "\n"
    cacheObject += "title " + itemObj.title + "\n"
    cacheObject += "parts " + itemObj.parts.mkString("Array(", ",", ")") + "\n"
    cacheObject += "descendants " + itemObj.descendants.toString + "\n"
    cacheObject
  }

  /**
   * precondition: give an user object
   * post condition: returns value of an user object in String
   * @param userObj - passed object
   * @return string from user
   */
  def toCacheObject(userObj : UserObject): String = {
    var cacheObject = "---\n"
    cacheObject += userObj.id + "\n"
    cacheObject += "created " + userObj.created.toString + "\n"
    cacheObject += "karma " + userObj.karma.toString + "\n"
    cacheObject += "about " + userObj.about + "\n"
    cacheObject += "submitted " + userObj.submitted.mkString("Array(", ",", ")") + "\n"
    cacheObject
  }

  /**
   * precondition: give an String object
   * post condition: returns value of an String object in item
   * @param cacheObj - passed object
   * @return item from string
   */
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

    id = lines.head.toInt
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
      val stringKids = lines.head.drop(12)
      kids = toIntArray(stringKids)
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
      val stringParts = lines.head.drop(13)
      parts = toIntArray(stringParts)
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

  /**
   * precondition: give an String object
   * post condition: returns value of an String object in user
   * @param cacheObj - passed object
   * @return user from string
   */
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
      created = lines.head.drop(8).toLong
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("karma")){
      karma = lines.head.drop(6).toInt
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("about")){
      about = lines.head.drop(6)
      lines = lines.drop(1)
    }
    if (lines.head.startsWith("submitted")){
      val stringSubmitted = lines.head.drop(16)
      submitted = toIntArray(stringSubmitted)
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

  /**
   * @param itemId - passed id
   * @param itemType - passed type
   * @return we look for an object with the given id in the cache of its type
   */
  def getCacheObject(itemId : String, itemType: String) : String = {
    var cacheObj = itemId + "\n"
    if (exists(itemId)){
      itemType match {
        case "user" => getCacheObj(cachePathUsers, 5)
        case _ => getCacheObj(cachePathItems, 15)
      }
    }

    /**
     * precondition: give a correct path and reasonable limit
     * post condition: returns value of an String object in item
     * @param path - path in string
     * @param counterLimit - till what we count
     * inner function to perform the same functionality for two different types
     */
    def getCacheObj(path: String, counterLimit: Int): Unit = {
      val cacheFile = new File(path)
      existException(cacheFile)
      val cacheLine = getLines(itemType)
      var found: Boolean = false
      var counter: Int = 0
      /**
       * inner function for loop
       */
      def gettingLines(): Unit ={
        for (line <- cacheLine){
          if (line.startsWith(itemId)) found = true
          if (counter > counterLimit || (line.startsWith("---") && counter == counterLimit)) return
          if (found) {
            counter += 1
            cacheObj += line + "\n"
          }
        }
      }
      gettingLines()
    }
    cacheObj
  }

  /**
   * precondition: itemID is valid
   * post condition: true if item exists in cache, else false
   * @param itemId - passed id of the item
   * @return true if item exists in cache
   */
  def exists(itemId: String): Boolean = {
    val lines = getAll
    for(line <- lines) {
      if (line.startsWith(itemId)) {
        return true
      }
    }
    false
  }

  /**
   * precondition: itemID is valid, itemType is user or item, newCacheObject is filled according to the rules
   * post condition: none
   * @param itemId is id of an item which is to be replaced
   * @param itemType specifies item type
   * @param newCacheObject is the data which should be put instead of old data at itemId
   */
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


  /**
   * @return all lines of cache as an array
   */
  def getAll: Array[String] = getLines("user").concat(getLines("item"))

  /**
   * adds given cache ovject to the cache
   * @param cacheObject which will be saved
   * @param typeOfObj where it has to go
   */
  /*
    * CacheObject ==
    * ---
    * id
    * the rest
    * */
  def add(cacheObject : String, typeOfObj : String) : Unit =
    {
      typeOfObj match {
        case "user" => write(cachePathUsers, cacheObject)
        case "item" => write(cachePathItems, cacheObject)
      }
    }

  /**
   * @param path where data should be written
   * @param cacheObject what data should be written
   */
  def write(path : String, cacheObject : String): Unit = {
    val cacheFile = new File(path)
    if(!cacheFile.exists()) cacheFile.createNewFile()
    val lines = toLines(cacheObject)
    val writer = new FileWriter(cacheFile, true)
    for (line <- lines) writer.append(line + '\n')

    writer.close()
  }

  /**
   * clears cache
   */
  def clearCache() : Unit = {
    val writerItems = new FileWriter(cachePathItems)
    val writerUsers = new FileWriter(cachePathUsers)
    writerItems.append("")
    writerUsers.append("")
    writerItems.close()
    writerUsers.close()
  }
}

/**
 *
 */
object CacheFile extends CacheFile(cachePathItems = "src/main/scala/cache/items/items", cachePathUsers = "src/main/scala/cache/users/users") {

  /**
   * @param itemObj - passed object
   * @return
   */
  override def toCacheObject(itemObj: ItemObject): String = super.toCacheObject(itemObj)

  /**
   * @param userObj - passed object
   * @return
   */
  override def toCacheObject(userObj: UserObject): String = super.toCacheObject(userObj)

  /**
   * @param cacheObj - passed object
   * @return
   */
  override def toItemObject(cacheObj: String): ItemObject = super.toItemObject(cacheObj)

  /**
   * @param cacheObj - passed object
   * @return
   */
  override def toUserObject(cacheObj: String): UserObject = super.toUserObject(cacheObj)

  /**
   * @param itemId - passed id of the item
   * @param itemType - passed type of the object
   * @return
   */
  override def getCacheObject(itemId: String, itemType: String): String = super.getCacheObject(itemId, itemType)

  /**
   * @param itemId - passed id
   * @param itemType - passed type
   * @param newCacheObject - new replacement
   */
  override def replace(itemId: String, itemType: String, newCacheObject: String): Unit = super.replace(itemId, itemType, newCacheObject)

  /**
   * @return
   */
  override def getAll: Array[String] = super.getAll

  /**
   * @param cacheObject - passed object
   * @param typeOfObj - passed type
   */
  override def add(cacheObject: String, typeOfObj: String): Unit = super.add(cacheObject, typeOfObj)

  /**
   * @param path - passed path in string
   * @param cacheObject - passed object
   */
  override def write(path: String, cacheObject: String): Unit = super.write(path, cacheObject)

  /**
   *
   */
  override def clearCache(): Unit = super.clearCache()
}