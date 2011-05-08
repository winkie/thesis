package vs.thesis

import scala.util.Random

class RichardsonsGenerator(N: Int, dimension: Int) {
  val rnd = new Random(System.nanoTime())

  private def selectDimple(probs_a: List[Double]): Int = {
    var probs = probs_a
    var total = probs.sum
    probs = probs.map(_ / total)
    val intervals = new Array[Double](probs.size)
    total = 0
    for (i <- 0 to probs.size - 1) {
      total += probs(i)
      intervals(i) = total
    }

    val x = rnd.nextDouble()
    for (i <- 0 to probs.size - 1) {
      if (x < intervals(i))
        return i
    }

    throw new RuntimeException("Cannot find interval for x = " + x)
  }

  def generate(distance:List[Int] => Double): Diagram = {
    var diagram: Diagram = null
    if (dimension == 2)
      diagram = new Diagram2
    else if (dimension == 3)
      diagram = new Diagram3
    else
      throw new RuntimeException("Cannot generate non-2D diagrams");

    while (diagram.count() < N) {
//      println(diagram.mDimples)

      val dimples = diagram.getDimples()
      var ind = -1;
      if (dimples.size == 1)
        ind = 0
      else
        ind = selectDimple(dimples.map(distance))

//      println("Filling " + dimples(ind))

      diagram.fillDimple(dimples(ind))
    }
    return diagram
  }
}