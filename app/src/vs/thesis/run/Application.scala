package vs.thesis.run

import java.io.{FileWriter, File, BufferedWriter}
import vs.thesis._
import minimization._
import visualization._
import javax.swing.{JButton, JFrame}
import java.awt.event.{ActionListener, ActionEvent}
import java.awt.Event

object Application {
  implicit def actionPerformedWrapper(func: (ActionEvent) => Unit) =
       new ActionListener { def actionPerformed(e:ActionEvent) = func(e) }

  def visual(args: Array[String]) {
    val func = ((l: List[Int]) =>
      math.sqrt(math.pow(l(0), 0.16) + math.pow(l(1), 0.16)))

    val N = 10000;
    val g = new RichardsonsGenerator(2)
    val d = g.generate(func, N)

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

  def meanDiagram2(l: List[Diagram2]): List[(Int, Double)] = {
    val dl = l.map(_.getDimples())
    val maxX = dl.map(_.map((l: List[Int]) => l(0)).max).max

    var arr = Array.fill(maxX + 1){Array(0, 0)}

    dl.foreach(_.foreach((l: List[Int]) => {
      arr(l(0))(0) += l(1)
      arr(l(0))(1) += 1
    }))

    var res = List[(Int, Double)]()
    for (i <- 0 to maxX) {
      if (arr(i)(0) != 0)
        res = res ::: List((i, arr(i)(0) * 1.0 / arr(i)(1)))
    }
    return res
  }

  def testOptimum() {
    val gen = new RichardsonsGenerator(2)
    //val (a, b) = (1.16, 0.45)
    //val (a, b) = (0.982, 0.380)
    //val (a, b) = (2.0, 0.192)
    //val (a, b) = (1.0, 0.453)
    //val (a, b) = (2.029, 0.209)
    //val (a, b) = (1.307, 0.303)
    val (a, b) = (2.0, 0.16)
    //val (a, b) = (2.0, 0.13)

    val f = (l: List[Int]) =>
      math.pow(math.pow(l(0), a) + math.pow(l(1), a), b)
    val f2 = (l: List[Int]) => 1.0
    val d = gen.generate(f, 10000)

    val arr = List.fill(100) {gen.generate(f, 10000).asInstanceOf[Diagram2]}
    val mean = meanDiagram2(arr)

    val c = math.Pi / math.sqrt(6)
    val norm = math.sqrt(d.count())

    val bw = new BufferedWriter(new FileWriter(new File("2d100.txt")));

    for (cor <- mean) { //d.getCorners()) {
      bw.write("" + math.exp(-c * (cor._1 / norm)) +
               " " + math.exp(-c * (cor._2 / norm)))
      bw.newLine();
    }

    bw.close();

    println(Diagram2.distanceToUniform(d))
    println(Diagram2.distanceToUniform2(d))
    (new D2Visualizer()).show(d)
  }

  def visual3D() {
    val generator = new RichardsonsGenerator(3)
    val weight = (x: List[Int]) => 1.0
    val d = generator.generate(weight, 1000)

    val v = new D3Visualizer()
    v.show(d)
  }

  def gui() {
    val frm = new JFrame("Generator")
    val btn2d = new JButton("Generate 2D")
    val btn3d = new JButton("Generate 3D")

    val generator2 = new RichardsonsGenerator(2)
    val generator3 = new RichardsonsGenerator(3)
    val weight = (x: List[Int]) => (1.0)

//    btn2d.addActionListener((e: Event) => {
//
//    })
  }

  def weightPlot() {
    val f = (a:Double, b: Double) =>
      (l: List[Int]) => math.pow(math.pow(l(0), a) + math.pow(l(1), a), b)

    val gen = new RichardsonsGenerator(2)

    val N = 1000
    val (al, ar) = (0.01, 2.0)
    val (bl, br) = (0.01, 1.0)

    val bf = new BruteForceSolver(1000)
    val m = bf.min((l: List[Double]) => (l(0)*l(0) + l(1)*l(1)), List(0.0, 0.0),
                   0.001, List((-10.0, 10.0), (-5.0, 5.0)))
    println(m)

    throw new RuntimeException("stop")

    val bw = new BufferedWriter(new FileWriter(new File("weightPlot.txt")));

    //bw.write("0 ");
    //for (j <- bl until br by 0.01) {
    //  bw.write("" + j + " ")
    //}
    //bw.write("\n");

    for (i <- al until ar by 0.1) {
      for (j <- bl until br by 0.05) {
        println((i, j))
        val diag = gen.generate(f(i, j), N)
        val dist = Diagram2.distanceToUniform3(diag)

        println(dist)
        bw.write("" + i +  " " + j + " " + dist + "\n")
      }
    }
    bw.close();
  }

  def main(args: Array[String]) = {
    //val d = new Diagram2
    //d.fillDimple(List(0, 0))
    //d.fillDimple(List(0, 1))
    //d.fillDimple(List(1, 0))
    //d.fillDimple(List(2, 0))
    //d.fillDimple(List(3, 0))

    //visual3D()
    //visual(args)
    testOptimum()
    //weightPlot()
  }
}
