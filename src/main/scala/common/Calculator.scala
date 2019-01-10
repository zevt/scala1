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

  def calculate(expression: String): Int = {
    val stack = new Stack[Int]
    val tokens = expression.split(" ")
    for (token <- tokens) {
      if (!handleOperator(token, stack) && !handleNumber(token, stack)) {
        throw new IllegalArgumentException("Invalid token: " + token)
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
