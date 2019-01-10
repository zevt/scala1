package common

import scala.collection.mutable.MutableList
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
      val tokens = expression.split(" ")
            
      val stack = new Stack[Int]
      for (token <- tokens) {
        token match {
          case "+" =>
            val rhs = stack.pop()
            val lhs = stack.pop()
            stack.push(rhs + lhs)
          case "-" =>
            val rhs = stack.pop()
            val lhs = stack.pop()
            stack.push(lhs - rhs)
          case "*" =>
            val rhs = stack.pop()
            val lhs = stack.pop()
            stack.push(rhs * lhs)
          case "/" =>
            val rhs = stack.pop()
            val lhs = stack.pop()
            stack.push(lhs / rhs)
          case _ => try {
            stack.push(token.toInt)
          } catch {
            case _: NumberFormatException => throw new IllegalArgumentException("Invalid Token: " + token)
          }
        }
      }
      println(stack.head)

    }


  }

  def handleNumber(token: String, stack: List[Int]): Boolean = {
    true
  }
}
