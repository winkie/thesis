package vs.thesis.minimization

trait IMultiDimSearch {
  type Vec = List[Double]
  def min(f: (Vec => Double), start: Vec, eps: Double,
          bounds: List[(Double, Double)]): Vec
}
