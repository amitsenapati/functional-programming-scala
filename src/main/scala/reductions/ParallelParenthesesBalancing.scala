package reductions

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def balance(chars: Array[Char]): Boolean = {
    var count = 0
    for(c <- chars){
      if(c.equals('(')) count += 1
      if(c.equals(')')) count -= 1
      if (count < 0) return false
    }
    if(count == 0) true else false
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int):(Int, Int)  = {
      var leftCount = 0
      var rightCount = 0
      var start = idx
      while (start < until) {
        if(chars(start).equals('(')) leftCount += 1
        if(chars(start).equals(')')) {
          if (leftCount > 0) leftCount += -1 else rightCount += 1
        }
        start += 1
      }
      (leftCount, rightCount)
    }

    def reduce(from: Int, until: Int): (Int, Int) = {
      if((until - from) < threshold) return traverse(from, until, 0, 1)
      val ((l1, r1), (l2, r2)) = parallel(
        reduce(from, (from + until) / 2),
        reduce((from + until) / 2, until)
      )
      val leftCount = l1 + l2
      val rightCount = r1 + r2
      if(leftCount < rightCount) (0, rightCount - leftCount) else (leftCount - rightCount, 0)
    }

    val (leftCount, rightCount) = reduce(0, chars.length)
    leftCount == rightCount && leftCount == 0
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
