package vs.thesis.minimization

class BruteForceSolver(n: Int = 10) extends IMultiDimSearch {
  override def min(f: (Vec => Double), start: Vec, eps: Double,
                   bounds: List[(Double, Double)] = null): Vec = {
    if (bounds == null)
      throw new RuntimeException("Can't do unconstrained minimization")

    println(bounds(0))
    val counters = Array.fill(bounds.size){0}
    val total = counters.size * (n - 1)
    val deltas = bounds.map((p: (Double, Double)) => (p._1, (p._2 - p._1) / n))
    var minVal = Double.MaxValue
    var minBlock = counters
    while (counters.sum < total) {
      //println(counters(0))
      val x = deltas.zip(counters).map((p: ((Double, Double), Int)) =>
        p._1._1 + (p._2 + 0.5) * p._1._2)
      val fx = f(x)
      if (fx < minVal) {
        minVal = fx
        minBlock = counters.clone()
      }

      var i = counters.size - 1
      while (i >= 0 && counters(i) == n - 1)
        i -= 1

      if (i != -1) {
        counters(i) += 1
        for (j <- (i + 1) until counters.size)
          counters(j) = 0
      }
    }

    println("Min delta = " + deltas.map(_._2).min)
    if (deltas.map(_._2).min < eps)
      return deltas.zip(minBlock).map((p: ((Double, Double), Int)) =>
        p._1._1 + (p._2 + 0.5) * p._1._2)
    else
      return min(f, start, eps, deltas.zip(minBlock).
        map((p: ((Double, Double), Int)) =>
        (p._1._1 + (p._2) * p._1._2, p._1._1 + (p._2 + 1) * p._1._2)))
  }
}