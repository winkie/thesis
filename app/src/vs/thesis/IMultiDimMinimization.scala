package vs.thesis

trait IMultiDimMinimization {
  type Vec = List[Double]
  def min(f: (Vec => Double), start: Vec, eps: Double): Vec
}
