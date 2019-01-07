package common

object Func {
  /**
    * This function demo match syntax in Scala, and wildcard matching using underscore character _
    * @param x
    * @return
    */
  def f(x: Int): String = {
    x match {
      case 0 => "Zero"
      case 1 => "One"
      case _ => "Plural"
    }
  }


}
