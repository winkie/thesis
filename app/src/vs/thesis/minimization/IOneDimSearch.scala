package vs.thesis.minimization

trait IOneDimSearch {
  def min(f: (Double => Double),
	  right: Double, left: Double,
	  eps: Double): Double
}
