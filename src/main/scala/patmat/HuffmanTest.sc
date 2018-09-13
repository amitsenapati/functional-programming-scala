import scala.collection.mutable

val chars: List[Char] = List('a', 'b', 'a')


def times(chars: List[Char]) : List[(Char, Int)] = {
  val hashMap = new mutable.HashMap[Char, Int]
  for(char <- chars){
    val int = hashMap.getOrElse(char, 0)
    hashMap(char) = int + 1
  }
//  println("Hash Map values" + hashMap)

  val pairList: List[(Char, Int)] =  List[(Char, Int)]()
  for ((k, v) <- hashMap) {
    val pair: (Char, Int) = (k, v)
    pairList ::: List(pair)
    println(pairList)
  }
  pairList
}

val res: List[(Char, Int)] = times(List('a', 'b', 'a'))
println(res)
