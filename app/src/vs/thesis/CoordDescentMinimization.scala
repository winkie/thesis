package vs.thesis

class CoordDescentMinimization extends IMultiDimMinimization {

  def norm2(a: Vec, b: Vec): Double = {
    return a.zip(b).map((p: (Double, Double)) => 
      (math.pow(p._1 - p._2, 2))).foldLeft(0.0)(_ + _)
  }

  def step(a: Vec, b: Vec, alpha: Double): Vec = {
    return a.zip(b).map((p: (Double, Double)) => (p._1 + alpha * p._2))
  }

  override def min(f: (Vec => Double), start: Vec, eps: Double): Vec = {
    val oneDimMinimizer = new GoldenRatioMinimization()

    val n = start.length
    val zeros = Array.fill(n){0.0}

    var (x, xPrev) = (start, zeros.toList)
    var i = 0

    while (norm2(x, xPrev) >= eps * eps) {
      zeros(i) = 1.0
      val dir = zeros.toList
      zeros(i) = 0.0

      //Only positive range mod
      val alphaMin = -x(i) + 0.001
      val alphaMax = math.abs(alphaMin) * 2.0

      val alpha = oneDimMinimizer.min((_alpha: Double) => 
	f(step(x, dir, _alpha)), alphaMin, alphaMax, 0.0001)
      
      xPrev = x
      x = step(x, dir, alpha)
      println("\tStep: " + x)
      i = (i + 1) % n   
    }
    return x
  }
}
