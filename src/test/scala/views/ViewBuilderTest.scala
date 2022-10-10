package views

import api.objects.ItemObject
import org.scalatest.funsuite.AnyFunSuite
import views.ViewBuilder.buildTime

import scala.Console.{BOLD, RESET, UNDERLINED}

class ViewBuilderTest extends AnyFunSuite{
  private def bold(text: String): String = s"$RESET$BOLD$text$RESET"


  test("fromHTML no tags test"){
    val text: String = " lal la la la laa"
    assert(ViewBuilder.fromHTML(text) == text)
  }
  test("fromHTML p tags are in"){
    val textP: String = "aaa<p>bbb"
    assert(ViewBuilder.fromHTML(textP) == "aaa\nbbb")
  }
  test("fromHTML <i>/<em> tags are in"){
    val textI: String = "aaa<i>baba</i>"
    val i: String = "baba"
    assert(ViewBuilder.fromHTML(textI) == s"aaa$RESET$UNDERLINED$i$RESET")
    val textEM: String = "u uu uuuu<em>baba</em>"
    assert(ViewBuilder.fromHTML(textEM) == s"u uu uuuu$RESET$UNDERLINED$i$RESET")
  }
  test("fromHTML <b>/<strong> tags are in"){
    val textI: String = "aaa<b>bbb</b>"
    val b: String = "bbb"
    assert(ViewBuilder.fromHTML(textI) == s"aaa$RESET$BOLD$b$RESET")
    val b2: String = "d d d"
    val textEM: String = "aaa<strong>d d d</strong>"
    assert(ViewBuilder.fromHTML(textEM) == s"aaa$RESET$BOLD$b2$RESET")
  }
  test("fromHTML all tags are in"){
    val textAll: String = " <strong> weak </strong> <p> <i> strong </i>"
    val partOut = " weak "
    val partCur = " strong "
    val output: String = s" $RESET$BOLD$partOut$RESET \n $RESET$UNDERLINED$partCur$RESET"
    assert(ViewBuilder.fromHTML(textAll) == output)
  }
  test("buildItemView test"){
    val itemObject = ItemObject(id = 1, deleted = false, itemType = "story", by = "test", time = 1111111, text = "This is test text", dead = false, parent = 11, poll = -1, kids = Array(1,2), url = "test.com/test", score = 1, title = "Test story", parts = Array(), descendants = -1)
    var storyString = bold(itemObject.title) + " (" + itemObject.url + ")\n"
    storyString += itemObject.score + " points by " + bold(itemObject.by)
    storyString += " at " + buildTime(itemObject.time) + " "
    storyString += " | " + bold(itemObject.kids.length.toString) + " comments\n"
    assert(ViewBuilder.buildItemView(itemObject) == storyString)
  }
}
