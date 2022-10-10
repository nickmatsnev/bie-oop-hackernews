package time

import org.scalatest.funsuite.AnyFunSuite

class TimeBuilderTest extends AnyFunSuite{

  test("getTime test"){
    val fiveYearsOneSecond: Long = 31556926 * 5 + 1
    val dtFY = DateTime(5,0,0,0,0,1)
    assert(TimeBuilder.getTime(fiveYearsOneSecond) == dtFY)
    val bigDate: Long = 31556926 * 5 + 2629743 * 6 + 86400 * 3
    val bigDt = DateTime(5,6,3,0,0,0)
    assert(TimeBuilder.getTime(bigDate) == bigDt)
  }
  test("getWeekDay test"){
    val dateTime: DateTime = TimeBuilder.getTime(1665418258)
    assert(TimeBuilder.getWeekDay(dateTime) == 6)
  }
}
