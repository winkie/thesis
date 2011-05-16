package vs.thesis

class WeightFitting(weightTemplate: List[Double] => (List[Int] => Double),
		    generator: IGenerator,
		    distance: IDiagram => Double,
		    minimizator: IMultiDimMinimization) {

  private def minFunction(x: List[Double]): Double = {
    println("\t\t\tWeight func params: " + x)
    var weight = weightTemplate(x)
    val diags = List.fill(10) {generator.generate(weight)}

    val meanDistance = diags.map(distance).foldLeft(0.0)(_ + _) / diags.size

    println(meanDistance)
    return meanDistance
  }

  def fit(start: List[Double], eps: Double): List[Double] = {
    println("Start: " + start)
    return minimizator.min(minFunction, start, eps)
  }
}
