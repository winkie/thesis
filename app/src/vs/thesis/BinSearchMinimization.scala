package vs.thesis

class BinSearchMinimization(delta: Double = 0.00001) 
                            extends IOneDimMinimization {
  override def min(f: (Double => Double),
                   left: Double, right: Double,
                   eps: Double): Double = {
    var (a, b) = (left, right)
    while (b - a >= 2 * eps) {      
      val (x1, x2) = (0.5 * (a + b - delta), 0.5 * (a + b + delta))
      val (fx1, fx2) = (f(x1), f(x2))
      if (fx1 >= fx2) a = x1
      else b = x2
    }
    return 0.5 * (a + b)
  }
}
