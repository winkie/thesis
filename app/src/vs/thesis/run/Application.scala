package vs.thesis.run

import java.io.{FileWriter, File, BufferedWriter}
import vs.thesis._
import minimization._
import visualization._

object Application {
  def visual(args: Array[String]) {
    val func = ((l: List[Int]) =>
      math.sqrt(math.pow(l(0), 0.16) + math.pow(l(1), 0.16)))
    val g = new RichardsonsGenerator(10000, 2)
    val d = g.generate(func)

    val v = new D2Visualizer()
    //v.show(d)
    println("exit")

    val part = List(1234, 100, 70, 5, 4, 2)

    val e = new Simple2DEnumerator(part.sum)

    val dd = Diagram2(part)

    val num = e.diagramToNumber(dd)

    println(num)
    val dd2 = e.numberToDiagram(num)

    val ddd = Diagram2(List(10, 5, 4, 2))
    v.show(ddd)
  }

  def minimizationTest(args: Array[String]) = {
    val f = (x: Double) => (x*x - 4 * x + 4)
    println(f(2))
    val m = (new BinarySearch).min(f, -10, 10, 0.0001)
    println(m)

    val f2 = (x: List[Double]) =>
      (math.pow(x(0) - 3.0, 2) + math.pow(x(1) + 2, 2))

    val mdm = new CoordinateDescentSearch()

    println(mdm.min(f2, List(-1110.0, 1000.0), 0.0001))
  }

  def testOptimum() {
    val gen = new RichardsonsGenerator(5000, 2)
    //val (a, b) = (1.16, 0.45)
    //val (a, b) = (0.982, 0.380)
    //val (a, b) = (2.0, 0.192)
    //val (a, b) = (1.0, 0.453)
    //val (a, b) = (2.029, 0.209)
    val (a, b) = (1.307, 0.303)
    //val (a, b) = (2.0, 0.16)
    val f = (l: List[Int]) =>
      math.pow(math.pow(l(0), a) + math.pow(l(1), a), b)
    val f2 = (l: List[Int]) => 1.0
    val d = gen.generate(f)

    val c = math.Pi / math.sqrt(6)
    val norm = math.sqrt(d.count())

    val bw = new BufferedWriter(new FileWriter(new File("2d4.txt")));

    for (cor <- d.getCorners()) {
      bw.write("" + math.exp(-c * (cor(0) / norm)) +
               " " + math.exp(-c * (cor(1) / norm)))
      bw.newLine();
    }

    bw.close();

    println(Diagram2.distanceToUniform(d))
    println(Diagram2.distanceToUniform2(d))
    (new D2Visualizer()).show(d)
  }

  def main(args: Array[String]) = {
    //val d = new Diagram2
    //d.fillDimple(List(0, 0))
    //d.fillDimple(List(0, 1))
    //d.fillDimple(List(1, 0))
    //d.fillDimple(List(2, 0))
    //d.fillDimple(List(3, 0))

    //visual(args)
    testOptimum()
  }
}
