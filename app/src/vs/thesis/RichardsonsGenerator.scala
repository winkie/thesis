package vs.thesis

import scala.util.Random

class RichardsonsGenerator(dim: Int) extends IGenerator {
  val rnd = new Random(System.nanoTime())

  private def selectDimple(probsArgs: List[Double]): Int = {
    var probs = probsArgs
    var total = probs.sum
//    println("Probs: " + probs)
    probs = probs.map(_ / total)
//    println("ProbsNorm: " + probs)
    val intervals = new Array[Double](probs.size)
    total = 0
//    print("Intervals: (")
    for (i <- 0 to probs.size - 1) {
      total += probs(i)
      intervals(i) = total
//      print(" " + intervals(i) + ",")
    }
//    println(")")

    val x = rnd.nextDouble()
    for (i <- 0 to probs.size - 1) {
      if (x < intervals(i))
        return i
    }

    throw new RuntimeException("Cannot find interval for x = " + x)
  }

  def generate(weight:List[Int] => Double, size: Int): IDiagram = {
    var diagram: IDiagram = null
    if (dim == 2)
      diagram = new Diagram2
    else if (dim == 3)
      diagram = new Diagram3
    else
      throw new RuntimeException("Cannot generate non-2D diagrams");

    while (diagram.count() < size) {
      val dimples = diagram.getDimples()
      var ind = -1;
      
//      println("Got " + dimples.size + " dimples")

      if (dimples.size == 1)
        ind = 0
      else
        ind = selectDimple(dimples.map(weight))

//      println("Filling " + ind)

      diagram.fillDimple(dimples(ind))
    }
    return diagram
  }
}
