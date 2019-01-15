package common

import scala.collection.mutable.Stack

/**
  * (1+2)*(3-4) represented as  1 2 + 3 4 - *
  */
object Calculator {
  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      throw new IllegalArgumentException("Require one argument")
    } else {
      val expression = args(0)
      println(calculate(expression))
    }

  }

  trait Operator {
    def operate(lhs: Int, rhs: Int): Int
  }

  object Operator {
    val operators: Map[String, Operator] =
      Map("+" -> Add,
        "-" -> Subtract,
        "*" -> Multiply,
        "/" -> Divide)

    def unapply(token: String): Option[Operator] = operators.get(token)
  }

  case object Add extends Operator {
    override def operate(lhs: Int, rhs: Int): Int = lhs + rhs

    override def toString = "+"
  }

  case object Subtract extends Operator {
    override def operate(lhs: Int, rhs: Int): Int = lhs - rhs

    override def toString = "-"
  }

  case object Multiply extends Operator {
    override def operate(lhs: Int, rhs: Int): Int = lhs * rhs

    override def toString = "*"
  }

  case object Divide extends Operator {
    override def operate(lhs: Int, rhs: Int): Int = lhs / rhs

    override def toString = "/"
  }

  object Number {
    def unapply(token: String): Option[Int] = try {
      Some(token.toInt)
    } catch {
      case _: NumberFormatException => None
    }
  }

  sealed trait Expression
  case class NumberExpression(value: Int) extends Expression
  case class OperatorExpression(lhs: Expression, rhs: Expression, op: Operator) extends Expression

  def parse(expression: String): Expression = {
    val stack = new Stack[Expression]
    for (token <- expression.split(" ")) {
      token match {
        case Number(num) => stack.push(NumberExpression(num))
        case Operator(op) =>
          val rhs = stack.pop()
          val lhs = stack.pop()
          stack.push(OperatorExpression(lhs, rhs, op))
        case _ => new IllegalAccessException(s"Illegal token: $token")
      }
    }
    stack.pop()
  }

  def calculate(expression: Expression): Int = expression match {
    case NumberExpression(value) => value
    case OperatorExpression(lhs, rhs, op) => op.operate(calculate(lhs), calculate(rhs))
  }

  def toInfix(expression: Expression): String = expression match {
    case NumberExpression(num) => num.toString
    case OperatorExpression(lhs, rhs, op) => s"(${toInfix(lhs)} + ${op.toString} + ${toInfix(rhs)})"
  }
  /**
    * calculate a postfix notation expression
    * @param expression
    * @return
    */
  def calculate(expression: String): Int = {
    val stack = new Stack[Int]
    val tokens = expression.split(" ")
//    for (token <- tokens) {
//      if (!handleOperator(token, stack) && !handleNumber(token, stack)) {
//        throw new IllegalArgumentException("Invalid token: " + token)
//      }
//    }
    for (token <- tokens) {
      token match {
        case Number(num) => stack.push(num)
        case Operator(op) =>
          val lhs = stack.pop()
          val rhs = stack.pop()
          stack.push(op.operate(lhs, rhs))
        case _ => throw new IllegalArgumentException(s"Invalid token: $token")
      }
    }
    stack.pop()
  }

  def handleNumber(token: String, stack: Stack[Int]): Boolean = {
    try {
      stack.push(token.toInt)
      true
    } catch {
      case _: NumberFormatException => false
    }
  }

  def handleOperator(token: String, stack: Stack[Int]): Boolean = {
    token match {
      case "+" =>
        val rhs = stack.pop()
        val lhs = stack.pop()
        stack.push(rhs + lhs)
        true
      case "-" =>
        val rhs = stack.pop()
        val lhs = stack.pop()
        stack.push(lhs - rhs)
        true
      case "*" =>
        val rhs = stack.pop()
        val lhs = stack.pop()
        stack.push(rhs * lhs)
        true
      case "/" =>
        val rhs = stack.pop()
        val lhs = stack.pop()
        stack.push(lhs / rhs)
        true
      case _ => false
    }
  }

}
