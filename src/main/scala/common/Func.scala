package common

object Func {
  def f(x: Int): String = {
    x match {
      case 0 => "Zero"
      case 1 => "One"
      case _ => "Plural"
    }
  }


}
