package time

case class DateTime(year : Int, month : Int, day : Int, hour : Int, minute : Int, second : Int){
  /**
   * @return century of a given sample
   */
  def getCentury: Int = ((year + 1970) / 100).floor.toInt

  /**
   * @return string representation of DateTime
   */
  override def toString: String = day + "." + month + "." + (year+1970) + " " + hour + ":" + minute + ":" + second
}
