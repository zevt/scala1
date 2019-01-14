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
sealed abstract class Mass2 extends Ordered[Mass2] {
  def amount: Double
  def toGrams: Gram2
  override def compare(that: Mass2): Int = (this.toGrams.amount - that.toGrams.amount).toInt
}

case class Gram2(amount: Double) extends Mass2 {
  override def toGrams: Gram2 = this
  override def toString: String = amount + "(g)"
}

case class Miligram2(amount: Double) extends Mass2 {
  override def toGrams: Gram2 = Gram2(amount  / 1000)
  override def toString: String = amount + "(mg)"
}

case class  Kilogram2(amount: Double) extends Mass2 {
  override def toGrams: Gram2 = Gram2(amount * 1000)
  override def toString: String = amount + "(kg)"
}