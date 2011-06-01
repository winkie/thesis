package vs.thesis.run

import vs.thesis._
import minimization.{BruteForceSolver, GoldenSectionSearch, CoordinateDescentSearch}

object WeightFitting {
    def minimization(args: Array[String]) = {
      val gen = new RichardsonsGenerator(2)

      //val (alpha, beta) = (2.0, 0.16)

      val weightTemplate = ((l: List[Double]) => {
        (x: List[Int]) => {
          math.pow(math.pow(x(0), l(0)) + math.pow(x(1), l(0)), l(1))
        }
      })

      val N = 10000
      val minimizer = new BruteForceSolver(100)//CoordinateDescentSearch()
      val wf = new WeightFitting(N, weightTemplate, gen,
        Diagram2.distanceToUniform3, minimizer)
      val opt = wf.fit(List(2.0, 0.5), 0.01, List((0.001, 2.5), (0.001, 0.5)))

      /*
      val min2 = new GoldenSectionSearch()
      val opt = min2.min((a: Double) => {
        println("\t\t\tWeight func params: " + a)
        val weight = (x: List[Int]) =>
          math.pow(math.pow(x(0), 2.0) + math.pow(x(1), 2.0), a)
        val diags = List.fill(50) {gen.generate(weight, N)}

        val meanDistance = diags.map(Diagram2.distanceToUniform3).foldLeft(0.0)(_ + _) / diags.size

        println(meanDistance)
        meanDistance
      }, 0.001, 0.5, 0.001);
      */
      println(opt)
    }

    def main(args: Array[String]) = {
      minimization(args)
    }
  }
