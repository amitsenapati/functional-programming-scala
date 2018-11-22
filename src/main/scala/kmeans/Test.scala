package kmeans

import scala.collection.{GenSeq, IndexedSeq}

object Test {
  def main(args: Array[String]): Unit = {
    val p1 = new Point(1, 1, 0)
    val p2 = new Point(1, -1, 0)
    val p3 = new Point(-1, 1, 0)
    val p4 = new Point(-1, -1, 0)
    val points: GenSeq[Point] = IndexedSeq(p1, p2, p3, p4)
    val mean = new Point(0, 0, 0)
    val means: GenSeq[Point] = IndexedSeq(mean)

//    val points: GenSeq[Point] = IndexedSeq()
//    val mean = new Point(1, 1, 1)
//    val means: GenSeq[Point] = IndexedSeq(mean)

    val x = points.map(p => (p, findClosest(p, means)))
    println("x: " + x)
    val y = x.groupBy(_._2)
    println("y: " + y)
    val z = y.map {case (k,v) => (k, v.map(_._1))}
    println("z: " + z)
    val z1 = means.map(mean => (mean, z.getOrElse(mean, GenSeq[Point]()))).toMap
    println("z1: " + z1)
    val z2 = z1.map { case (k,v) => (k, findAverage(k, v))}
    println("z2: " + z2)
    val oldMeans = means
    val z3 = oldMeans.map(mean => z2.get(mean))
    println("z3: " + z3)
  }

  def findAverage(oldMean: Point, points: GenSeq[Point]): Point = if (points.isEmpty) oldMean else {
    var x = 0.0
    var y = 0.0
    var z = 0.0
    points.seq.foreach { p =>
      x += p.x
      y += p.y
      z += p.z
    }
    new Point(x / points.length, y / points.length, z / points.length)
  }


  def findClosest(p: Point, means: GenSeq[Point]): Point = {
    assert(means.nonEmpty)
    var minDistance = p.squareDistance(means.head)
    var closest = means.head
    var i = 1
    while (i < means.length) {
      val distance = p.squareDistance(means(i))
      if (distance < minDistance) {
        minDistance = distance
        closest = means(i)
      }
      i += 1
    }
    closest
  }
}
