package vs.thesis

class Diagram2 extends IDiagram {
  override def count() = mCount
  override def dimension() = 2

  override def getDimples(): List[List[Int]] = {
    return mDimples;
  }

  override def getCorners(): List[List[Int]] = {
    if (mDimples.size == 1)
      return null

    var corners = List[List[Int]]()
    for (i <- mDimples.size - 1 to 1 by -1) {
      val d1 = mDimples(i)
      val d2 = mDimples(i - 1)
      corners = List(d2(0), d1(1)) :: corners
    }
    return corners
  }

  def getPartition(): List[Int] = {
    var partition = List[Int]()

    //println(mDimples)

    mDimples.dropRight(1).zip(mDimples.drop(1))
      .foreach((ll: (List[Int], List[Int])) => {
        val (y1, y2) = (ll._1(1), ll._2(1))
        val h = y2 - y1
        for (i <- 0 until h) {
          partition = partition ::: List(ll._1(0))
        }
      })

    return partition
  }

  override def fillDimple(dimple: List[Int]): Unit = {
    val ind = mDimples.findIndexOf(_ == dimple)

    val up = (ind == mDimples.size - 1 || mDimples(ind + 1)(1) - dimple(1) > 1)
    val side = (ind == 0 || mDimples(ind - 1)(0) - dimple(0) > 1)

    val head = mDimples.slice(0, ind);
    val tail = mDimples.slice(ind + 1, mDimples.size)

    if (up && side) {
      mDimples = head ::: List(List(dimple(0) + 1, dimple(1))) :::
        List(List(dimple(0), dimple(1) + 1)) ::: tail
    } else if (up) {
      mDimples = head ::: List(List(dimple(0), dimple(1) + 1)) ::: tail
    } else if (side) {
      mDimples = head ::: List(List(dimple(0) + 1, dimple(1))) ::: tail
    } else {
      mDimples = head ::: tail
    }
    mDimples = mDimples.filterNot(_ == List())
    mCount = mCount + 1
  }

  var mDimples = List(List(0, 0))
  var mCount = 0
}

object Diagram2 {
  def apply(partition: List[Int]): Diagram2 = {
    partition.sortWith(_ > _)
    val d = new Diagram2
    d.mCount = partition.sum

    d.mDimples = List(List(partition(0), 0))
    for (i <- 1 until partition.size) {
      if (partition(i) != partition(i - 1))
        d.mDimples = d.mDimples ::: List(List(partition(i), i))
    }
    d.mDimples = d.mDimples ::: List(List(0, partition.size))

    return d
  }

  def distanceToUniform(diagram: IDiagram): Double = {
    val diagram2 = diagram.asInstanceOf[Diagram2]
    val c = math.Pi / math.sqrt(6)
    val targetX = (x: Double) => -math.log((1 - math.exp(-c * x))) / c

    def dist(x: Double, y: Double): Double = {
      val (xv, yv) = (targetX(x) - y, targetX(y) - x)
      return math.sqrt(xv * xv + yv * yv)
    }

    val norm = math.sqrt(diagram2.count())

    return diagram2.getCorners()
      .map((l: List[Int]) => dist(l(0) / norm, l(1) / norm))
      .max
      //.foldLeft(0.0)(_ + _)
  }

  def distanceToUniform2(diagram: IDiagram): Double = {
    val diagram2 = diagram.asInstanceOf[Diagram2]
    val c = math.Pi / math.sqrt(6)
    val norm = math.sqrt(diagram2.count())

    def dist(x: Double, y: Double): Double = {
      val (lx, ly) = (math.exp(-c * x), math.exp(-c * y))
      return math.abs(lx + ly - 1) / math.sqrt(2)
    }

    return diagram2.getCorners()
      .map((l: List[Int]) => dist(l(0) / norm, l(1) / norm))
      .max
      //.foldLeft(0.0)(_ + _)
  }
}
