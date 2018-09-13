package patmat

import patmat.Huffman._

import scala.collection.mutable

object MyApp extends App {

  val chars: List[Char] = List('a', 'b', 'a')

  def times(chars: List[Char]): List[(Char, Int)] = {
    val hashMap = new mutable.HashMap[Char, Int]
    for(char <- chars){
      val int = hashMap.getOrElse(char, 0)
      hashMap(char) = int + 1
    }

    var pairList: List[(Char, Int)] =  List[(Char, Int)]()
    for ((k, v) <- hashMap) {
      val pair: (Char, Int) = (k, v)
      pairList ::= pair
    }
    pairList
  }

//  println(times(chars))

//  println(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))))

  def combine(trees: List[CodeTree]): List[CodeTree] = {
    trees.length match {
      case 0 => trees
      case 1 => trees
      case _ => {
        val first = trees.head
        val treesAfterFirst = trees.tail
        val second = treesAfterFirst.head
        var treesAfterSecond: List[CodeTree] = treesAfterFirst.tail
        val node: CodeTree = makeCodeTree(first, second)
        treesAfterSecond ::= node
        treesAfterSecond.sortBy(weight)
      }
    }
  }

  val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('z', 5), Leaf('x', 4))
  println(combine(leaflist))
//  List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4))
}
