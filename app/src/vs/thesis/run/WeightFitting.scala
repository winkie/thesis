package vs.thesis.run

import vs.thesis._
import minimization.{GoldenSectionSearch, CoordinateDescentSearch}

object WeightFitting {
    def minimization(args: Array[String]) = {
      val gen = new RichardsonsGenerator(2)

      //val (alpha, beta) = (2.0, 0.16)

      val weightTemplate = ((l: List[Double]) => {
        (x: List[Int]) => {
          math.pow(math.pow(x(0), l(0)) + math.pow(x(1), l(0)), l(1))
        }
      })

//      val minimizer = new CoordinateDescentSearch()
//      val wf = new WeightFitting(weightTemplate, gen,
//        Diagram2.distanceToUniform2, minimizer)
//      val opt = wf.fit(List(2.0, 0.5), 0.01, List((0.1, 3.0), (0.001, 0.5)))

      val N = 10000
      val min2 = new GoldenSectionSearch()
      val opt = min2.min((a: Double) => {
        println("\t\t\tWeight func params: " + a)
        val weight = (x: List[Int]) =>
          math.pow(math.pow(x(0), 2.0) + math.pow(x(1), 2.0), a)
        val diags = List.fill(50) {gen.generate(weight, N)}

        val meanDistance = diags.map(Diagram2.distanceToUniform2).foldLeft(0.0)(_ + _) / diags.size

        println(meanDistance)
        meanDistance
      }, 0.001, 0.5, 0.001);

      println(opt)
    }

    def main(args: Array[String]) = {
      minimization(args)
    }
  }

import minimization.CoordinateDescentSearch

