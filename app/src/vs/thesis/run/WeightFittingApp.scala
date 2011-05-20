package vs.thesis.run

import vs.thesis._
import minimization.CoordinateDescentSearch

object WeightFittingApp {
    def minimization(args: Array[String]) = {
      val gen = new RichardsonsGenerator(5000, 2)

      //val (alpha, beta) = (2.0, 0.16)

      val weightTemplate = ((l: List[Double]) => {
        (x: List[Int]) => {
          math.pow(math.pow(x(0), l(0)) + math.pow(x(1), l(0)), l(1))
        }
      })

      val minimizer = new CoordinateDescentSearch()
      val wf = new WeightFitting(weightTemplate, gen,
        Diagram2.distanceToUniform2, minimizer)
      val opt = wf.fit(List(2.0, 0.5), 0.01, List((0.1, 3.0), (0.001, 0.5)))

      println(opt)
    }

    def main(args: Array[String]) = {
      minimization(args)
    }
  }

import minimization.CoordinateDescentSearch

