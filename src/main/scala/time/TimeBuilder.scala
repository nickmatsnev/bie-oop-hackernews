package time

object TimeBuilder {
  protected def timeLoop(seconds : Long, secondsPerUnit : Long) : Int={
    var secondsCountable = seconds
    var units = 0
    while (secondsCountable > secondsPerUnit) {
      secondsCountable -= secondsPerUnit
      units += 1
    }
    units
  }
  def getTime(Seconds : Long) : DateTime ={
    val yearInSec = 31556926
    val MonthInSec = 2629743
    val DayInSec = 86400
    val HourInSec = 3600
    val minInSec = 60

    val years = timeLoop(Seconds, yearInSec)
    var newSeconds = Seconds - years * yearInSec

    val months = timeLoop(newSeconds, MonthInSec)
    newSeconds = newSeconds - months * MonthInSec

    val days = timeLoop(newSeconds, DayInSec)
    newSeconds = newSeconds - days * DayInSec

    val hours = timeLoop(newSeconds, HourInSec)
    newSeconds = newSeconds - hours * HourInSec

    val minutes = timeLoop(newSeconds, minInSec)
    newSeconds = newSeconds - minutes * minInSec

    val seconds = newSeconds.toInt

    DateTime(years, months, days, hours, minutes, seconds)
  }

  def getWeekDay(dateTime : DateTime) : Int = {
    ((dateTime.day + (2.6 * dateTime.month - 0.2).floor - 2 * dateTime.getCentury + dateTime.year
    + (dateTime.year / 4).floor + (dateTime.getCentury / 4).floor) % 7).toInt
  }
}
