/**
  * @author Viet Quoc Tran vt 
  *         on 2019-01-03.
  * www.zeroexception.com
  */

class Rational(n: Int, d: Int = 1) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  override def toString = if (denom == 1) numer.toString else numer + "/" + denom

  def add(that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def + (that: Rational): Rational = {
    add(that)
  }

  def * (that: Rational): Rational = {
    new Rational(numer * that.numer, denom * that.denom)
  }
}