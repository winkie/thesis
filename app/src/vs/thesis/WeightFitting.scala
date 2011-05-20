package vs.thesis

import minimization.IMultiDimSearch

class WeightFitting(weightTemplate: List[Double] => (List[Int] => Double),
                    generator: IGenerator,
                    distance: IDiagram => Double,
                    minimizator: IMultiDimSearch) {

  private def minFunction(x: List[Double]): Double = {
    println("\t\t\tWeight func params: " + x)
    val weight = weightTemplate(x)
    val diags = List.fill(20) {generator.generate(weight)}

    val meanDistance = diags.map(distance).foldLeft(0.0)(_ + _) / diags.size

    println(meanDistance)
    return meanDistance
  }

  def fit(start: List[Double], eps: Double,
          bounds: List[(Double, Double)] = null): List[Double] = {
    println("Start: " + start)
    return minimizator.min(minFunction, start, eps, bounds)
  }
}

