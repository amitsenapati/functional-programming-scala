package forcomp

object week62 extends App{
  type Word = String
  type Occurrences = List[(Char, Int)]
  val dictionary: List[Word] = loadDictionary
  println(dictionary.size)
//  println(dictionary.take(100))

  def wordOccurrences(w: Word): Occurrences = {
    w.groupBy(c => c.toLower).mapValues(_.size).toList.sortBy(_._1)
  }

  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
    var myMap = scala.collection.mutable.Map[Occurrences, List[Word]]()
    for(w <- dictionary){
      val occurrences = wordOccurrences(w)
      myMap.get(occurrences) match {
        case Some(xs: List[Word]) => myMap.update(occurrences, xs:+ w)
        case None => myMap.put(occurrences, List(w))
      }
    }
    myMap.toMap
  }

//  println(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))))

  def wordAnagrams(word: Word): List[Word] = {
    dictionaryByOccurrences.get(wordOccurrences(word)).get
  }

//  println(wordAnagrams("married").toSet)
//  println(wordAnagrams("player").toSet)

//  https://stackoverflow.com/questions/50526892/scala-how-to-split-a-list-into-tuples-in-all-possible-ways
//  https://www.google.com/search?client=ubuntu&hs=lwm&channel=fs&ei=KyylW9v2CYfGvwTwi5LwBw&q=scala+list+of+tuples+generate+combinations&oq=scala+list+of+tuples+generate+combinations&gs_l=psy-ab.3...1824912.1843918.0.1844664.46.42.0.0.0.0.196.4485.19j23.42.0....0...1c.1.64.psy-ab..7.38.4091...0j0i67k1j0i22i30k1j35i39k1j0i20i263k1j33i22i29i30k1j33i160k1j33i21k1.0.l7A0w23htkI
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    if(occurrences.isEmpty) List(List())
    else {
      val head = occurrences.head
      val combos: List[Occurrences] = combinations(occurrences.tail)
      ( for {
        combo <- combos
        n <- 1 to head._2
      } yield (head._1, n) :: combo) ++ combos
    }
  }

  val abba = List(('a', 2), ('b', 2))
  println(combinations(abba))
//  println(combinations(Nil))

}
