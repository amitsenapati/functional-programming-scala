package forcomp

object week6 {
  type Word = String
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)]
//  val dictionary: List[Word] = loadDictionary
  val dictionary: List[Word] = List("ate", "eat", "tea")

  def wordOccurrences(w: Word): Occurrences = {
    w.groupBy(c => c.toLower).mapValues(_.size).toList.sortBy(_._1)
  }

  wordOccurrences("Robert")
//
//  def sentenceOccurrences(s: Sentence): Occurrences = {
//    wordOccurrences(s.mkString)
//  }

//  sentenceOccurrences(List("abcd", "e"))



//  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
//    var myMap = scala.collection.mutable.Map[Occurrences, List[Word]]()
//    for(w <- dictionary){
//      val occurrences = wordOccurrences(w)
//      myMap.get(occurrences) match {
//        case Some(xs: List[Word]) => myMap.update(occurrences, xs:+ w)
//        case None => myMap.put(occurrences, List(w))
//      }
//    }
//    myMap.toMap
//  }
//
//  dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1)))

//  def test = {
//    dictionary.size
//    dictionary.take(100)
//  }
}