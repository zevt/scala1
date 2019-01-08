package common

object Func {
  /**
    * This function demo match syntax in Scala, and wildcard matching using underscore character
    * @param x: Int
    * @return
    */
  def f(x: Int): String = {
    x match {
      case 0 => "Zero"
      case 1 => "One"
      case _ => "Plural"
    }
  }

  /**
    * Demonstrate local function
    */
  def gcd(a: Int, b: Int): Int = {
    def f(x: Int, y: Int): Int = {
      if (y == 0) x else f(y, x % y)
    }
    f(a,b)
  }


}
