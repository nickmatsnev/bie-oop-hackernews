package time

case class DateTime(year : Int, month : Int, day : Int, hour : Int, minute : Int, second : Int){
  def getCentury: Int = (year / 100).floor.toInt

  override def toString: String = day + "." + month + "." + year + " " + hour + ":" + minute + ":" + second
}
