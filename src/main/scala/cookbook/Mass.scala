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