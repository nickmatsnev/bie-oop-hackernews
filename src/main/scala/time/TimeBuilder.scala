package time

/**
 * i honestly did time package because i am narcissist
 */
object TimeBuilder {
  /**
   * precondition: seconds should be more than 0
   * post condition: none
   * @param Seconds - unix/epoch time in seconds
   * @return time wrapped in DateTime case class
   */
  def getTime(Seconds : Long) : DateTime ={
    /**
     * @param seconds passed number of seconds
     * @param secondsPerUnit passed time unit in seconds
     * @return time in the given measure, used as inner function for getTime
     */
    def timeLoop(seconds : Long, secondsPerUnit : Long) : Int = {
      var secondsCountable = seconds
      var units = 0
      while (secondsCountable > secondsPerUnit) {
        secondsCountable -= secondsPerUnit
        units += 1
      }
      units
    }
    val yearInSec = 31556926
    val MonthInSec = 2629743
    val DayInSec = 86400
    val HourInSec = 3600
    val minInSec = 60

    var years = timeLoop(Seconds, yearInSec)
    var newSeconds = Seconds - years * yearInSec

    var months = timeLoop(newSeconds, MonthInSec)
    newSeconds = newSeconds - months * MonthInSec

    var days = timeLoop(newSeconds, DayInSec)
    newSeconds = newSeconds - days * DayInSec

    var hours = timeLoop(newSeconds, HourInSec)
    newSeconds = newSeconds - hours * HourInSec

    var minutes = timeLoop(newSeconds, minInSec)
    newSeconds = newSeconds - minutes * minInSec

    val seconds = newSeconds.toInt

    if (seconds == 60){
      minutes += 1
      newSeconds = 0
      if (minutes == 60){
        hours += 1
        minutes = 0
      }
      if(hours == 24){
        days += 1
        hours = 0
        if(days == 32){
          months += 1
          days = 1
        }
        if(months == 12){
          years += 1
          months = 0
        }
      }
    }

    DateTime(years, months, days, hours, minutes, newSeconds.toInt)
  }

  /**
   * this is a universal formula. i could've done the same for the
   * getTime function and therefore avoid possible errorous outcome after first leap year
   * but i am lazy and it doesnt really matter for the sake of this course
   * if it would be however Scala subject,
   * I would've done a custom library with ALL java.util.Time functionality
   * Scala is really awesome, like TypeScript but with JVM:DDD
   * @param dateTime - date time object for which the weekday is desired
   * @return calculates a weekday from Mon to Sun of a given date
   */
  def getWeekDay(dateTime : DateTime) : Int = {
    ((dateTime.day + (2.6 * dateTime.month - 0.2).floor - 2 * dateTime.getCentury + dateTime.year
    + (dateTime.year / 4).floor + (dateTime.getCentury / 4).floor) % 7).toInt
  }
}
