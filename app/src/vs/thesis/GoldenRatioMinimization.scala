package vs.thesis

class GoldenRatioMinimization extends IOneDimMinimization {
  val phi = 0.5 * (math.sqrt(5) - 1)

  override def min(f: (Double => Double),
                   left: Double, right: Double,
                   eps: Double): Double = {
    var (a, b) = (left, right)
    var (x1, x2) = (b - (b - a) * phi, a + (b - a) * phi)
    var (fx1, fx2) = (f(x1), f(x2))
    while (b - a >= 2 * eps) {      
      if (fx1 >= fx2) {
        a = x1; x1 = x2; x2 = a + (b - a) * phi
        fx1 = fx2; fx2 = f(x2)
      } else {
        b = x2; x2 = x1; x1 = b - (b - a) * phi
        fx2 = fx1; fx1 = f(x1)
      }
    }
    return 0.5 * (a + b)
  }
}
