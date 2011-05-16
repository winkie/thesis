package vs.thesis

trait IOneDimMinimization {
  def min(f: (Double => Double),
	  right: Double, left: Double,
	  eps: Double): Double
}
