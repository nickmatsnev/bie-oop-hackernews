package time.enums

/**
 * Enumeration as match
 */
object Month {
  /**
   * @param noMonth integer representation of month from 1 to 12
   * @return string match of month
   */
  def apply(noMonth : Int): String = {
   noMonth match {
     case 1  => "January"
     case 2  => "February"
     case 3  => "March"
     case 4  => "April"
     case 5  => "May"
     case 6  => "June"
     case 7  => "July"
     case 8  => "August"
     case 9  => "September"
     case 10 => "October"
     case 11 => "November"
     case _ => "December"
   }
 }
}
