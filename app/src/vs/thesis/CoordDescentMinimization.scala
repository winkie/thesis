package vs.thesis

class CoordDescentMinimization extends IMultiDimMinimization {

  def norm2(a: Vec, b: Vec): Double = {
    return a.zip(b).map((p: (Double, Double)) => 
      (math.pow(p._1 - p._2, 2))).foldLeft(0.0)(_ + _)
  }

  def step(a: Vec, b: Vec, alpha: Double): Vec = {
    return a.zip(b).map((p: (Double, Double)) => (p._1 + alpha * p._2))
  }

  override def min(f: (Vec => Double), start: Vec, eps: Double,
                   bounds: List[(Double, Double)] = null): Vec = {
    val oneDimMinimizer = new GoldenRatioMinimization()

    val n = start.length
    val zeros = Array.fill(n){0.0}

    var (x, xPrev) = (start, zeros.toList)
    var i = 0

    while (norm2(x, xPrev) >= eps * eps) {
      zeros(i) = 1.0
      val dir = zeros.toList
      zeros(i) = 0.0

      var alphas: (Double, Double) = null

      if (bounds != null) {
        assert(bounds.size == n)
        val bi = bounds(i)
        assert(bi._2 >= bi._1)
        alphas = (bi._1 - x(i), bi._2 - x(i))
      } else {
        println("WARNING: Unconstrained minimization!")
      }

      val alpha = oneDimMinimizer.min((_alpha: Double) => 
        f(step(x, dir, _alpha)), alphas._1, alphas._2, eps * eps * 0.1)
      
      xPrev = x
      x = step(x, dir, alpha)
      println("\tStep: " + x)
      i = (i + 1) % n   
    }
    return x
  }
}
