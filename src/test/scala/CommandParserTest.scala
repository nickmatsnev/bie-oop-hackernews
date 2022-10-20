import api.apicalls.MockApiService
import api.objects.{ItemObject, UserObject}
import cache.CacheServiceMock
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

class CommandParserTest extends AnyFunSuite
{
  private val mockCache = new CacheServiceMock(ArrayBuffer(new ItemObject(2)),ArrayBuffer(new UserObject("test1")))
  private val mockApi = new MockApiService(mockCache, Array(new UserObject("test2"), new UserObject("vlad")), Array(2), Array(3))

  private val commandFactory = new CommandFactory(mockCache, mockApi)

  test("topstories command content test"){
    commandFactory.create("topstories", Array())
  }
  test("user command content test"){
    commandFactory.create("vlad", Array())
  }

  test("Usual requests"){
    val goodCommandNoHelp = Array("topstories", "--start=1", "--end=4")
    assert(CommandParser.splitCommand(goodCommandNoHelp)._2 sameElements Array("topstories", "--start=1", "--end=4"))
    val goodCommandHelp = Array("--help", "beststories", "--start=2")
    assert(CommandParser.splitCommand(goodCommandHelp)._2 sameElements Array("beststories", "--start=2"))
    val goodCommandEnd = Array("newstories", "--end=10")
    assert(CommandParser.splitCommand(goodCommandEnd)._2 sameElements Array("newstories", "--end=10"))
    val goodCommandPage = Array("askstories", "--page=1")
    assert(CommandParser.splitCommand(goodCommandPage)._2 sameElements Array("askstories", "--page=1"))
    val goodCommandShow = Array("jobstories", "--showTime=10", "--showSize=5")
    assert(CommandParser.splitCommand(goodCommandShow)._2 sameElements Array("jobstories", "--showTime=10", "--showSize=5"))
    val goodCommandShowEnd = Array("showstories", "--showTime=10", "--showSize=5", "--end=100")
    assert(CommandParser.splitCommand(goodCommandShowEnd)._2 sameElements Array("showstories", "--showTime=10", "--showSize=5", "--end=100"))
    val userCommand = Array("vlad")
    assert(CommandParser.splitCommand(userCommand)._2 sameElements Array("vlad"))
  }
  test("Basic help test"){
    var help = "Welcome to the command line feed of Hacker News!\n"
    help += "Input Format: [options] [command] [command-options]\n"
    help += "Commands:\n1. --help - used for getting the manual of the program.\n"
    help += "2. --help [command] will show help regarding the specific command\n"
    help += "3. --ttl=[value] will set up the refreshment time of cache.\n"
    help += "4. --clear-cache will clear cache.\n"
    help += "5. topstories [command-options] will show the output of the topstories command\n"
    help += "6. beststories [command-options] will show the output of the beststories command\n"
    help += "7. newstories [command-options] will show the output of the newstories command\n"
    help += "8. askstories [command-options] will show the output of the askstories command\n"
    help += "9. showstories [command-options] will show the output of the showstories command\n"
    help += "10. jobstories [command-options] will show the output of the jobstories command\n"
    help += "11. [user_id] will show user info and top stories of him/her with comments\n"
    help += "12. maxitem will the most rated item on Hacker News!\n"
    help += "Command Options:\n1. --page=[value] opens the specific page of stories\n2. --showSize=[value] allows limit" +
      "ed to [value] number of rows\n3. --showTime=[valueInSeconds] shows the time each page of items will be static.\n4. --start=[k]&--end==[n] where k <= n and k is the first page and n is the last" +
      " page of selected topic.\n"

    assert(CommandParser.defaultHelp() == help)
  }
}
