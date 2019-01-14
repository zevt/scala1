package cookbook

sealed abstract class Mass extends Comparable[Mass] {
  def amount: Double
  def toGrams: Gram
  override def compareTo(that: Mass): Int = (this.amount - that.amount).toInt
}

case class Gram(amount: Double) extends Mass {
  override def toGrams: Gram = this
  override def toString: String = amount + "(g)"
}

case class Miligram(amount: Double) extends Mass {
  override def toGrams: Gram = Gram(amount / 1000)
  override def toString: String = amount + "(mg)"
}

case class Kilogram(amount: Double) extends Mass {
  override def toGrams: Gram = Gram(amount * 1000)
  override def toString: String = amount + "(kg)"
}

//--------------------------------------------------------------------
/**
  * Using Ordered trait
  * https://www.scala-lang.org/api/2.12.0/scala/math/Ordered.html
  */

trait Measured {
  val amount: Double
  def symbol: String
  override def toString: String = amount + s"($symbol)"
}

sealed abstract class Mass2 extends Ordered[Mass2] with Measured {
  def toGrams: Gram2
  override def compare(that: Mass2): Int = (this.toGrams.amount - that.toGrams.amount).toInt
}

case class Gram2(amount: Double) extends Mass2 {
  val symbol = "g"
  override def toGrams: Gram2 = this
}

case class Miligram2(amount: Double) extends Mass2 {
  val symbol = "mg"
  override def toGrams: Gram2 = Gram2(amount  / 1000)
}

case class  Kilogram2(amount: Double) extends Mass2 {
  val symbol = "kg"
  override def toGrams: Gram2 = Gram2(amount * 1000)
}