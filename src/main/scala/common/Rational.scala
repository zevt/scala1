package common
import scala.language.implicitConversions


class Rational(n: Int, d: Int = 1) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  def this(n: Int) = this(n, 1)

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  override def toString: String = if (denom == 1) numer.toString else numer + "/" + denom

  def add(that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def +(that: Rational): Rational = {
    add(that)
  }

  def +(that: Int): Rational = {
    this + new Rational(that)
  }

  implicit def intToRational(x: Int): Rational = new Rational(x)

  def *(that: Rational): Rational = {
    new Rational(numer * that.numer, denom * that.denom)
  }
}