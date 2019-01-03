/**
  * @author Viet Quoc Tran vt 
  *         on 2019-01-03.
  * www.zeroexception.com
  */

class Rational(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d

  override def toString = numer + "/" + denom

  def add(that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }
}