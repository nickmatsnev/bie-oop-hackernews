package cache
import api.objects.ItemObject

import java.io.{File, FileWriter}
import scala.io.Source

class CacheFile {
  private val cachePathItems = "cache/items/items"
  private val cachePathUsers = "cache/users/users"

  // for replace
  def getCacheObject(itemId : String, itemType: String) : String = ???

  // for validating
  def replace(itemId : String, itemType: String, newCacheObject : String) : String = ???

  // for replace
  def getAll: Array[String] = ???

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
    val cacheObjMut = cacheObject
    if(!cacheFile.exists()) cacheFile.createNewFile()
    val lines = cacheObjMut.split(" ")
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
