package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
//    println("Pascal's Triangle")
//    for (row <- 0 to 10) {
//      for (col <- 0 to row)
//        print(pascal(col, row) + " ")
//      println()
//    }
//    println("balance check [())(] - " + balance("())(".toList))
//    println("Facrotial for number [6] - " + factorial(6))
    println("Sum of numbers from  [1-6] - " + sum(x => x)(1, 6))
    println("Sum of squared numbers from  [1-6] - " + sum(x => x * x)(1, 6))
    println("Sum of cube numbers from  [1-6] - " + sum(x => x * x * x)(1, 6))
    println("Sum of factorial numbers from  [1-6] - " + sum(factorial)(1, 6))

  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (c == 0 || c == r) 1
      else pascal(c-1, r-1) + pascal(c, r-1)
    }

  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def balanced(charsNew: List[Char], open: Int): Boolean = {
//        println("called with [" + charsNew.toString() + "] open braces [" + open + "]")
        if(charsNew.isEmpty)
          if (open == 0)  true
          else  false
        else {
          val head = charsNew.head
          if(head == '(') balanced(charsNew.tail, open + 1)
          else if(head ==')') {
            if (open > 0) balanced(charsNew.tail, open - 1) else false
          }
          else balanced(charsNew.tail, open)
        }
      }
      balanced(chars, 0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      def count(amount: Int, coinAtIndex: Int): Int = {
        if(amount < 0 || coinAtIndex < 0) return 0
        if (amount == 0) return 1
        count(amount, coinAtIndex -1) + count(amount - coins(coinAtIndex), coinAtIndex)
      }
      count(money, coins.length -1)
    }

  /**
    * Factorial tail recursive
     */
    def factorial(n: Int): Int = {
      @tailrec
      def tailRec(x: Int, result: Int): Int =
        if(x == 1) result
        else tailRec(x -1 , result * x)
      tailRec(n, 1)
    }

  /**
    * sum range of numbers tail recursive
    */
    def sum(f: Int => Int)(a: Int, b: Int): Int = {
      def loop(a: Int, acc: Int): Int = {
        if(a > b) acc
        else loop(a+1, acc + f(a))
      }
      loop(a, 0)
    }
  }
