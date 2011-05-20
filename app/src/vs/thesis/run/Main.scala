package vs.thesis.run

import vs.thesis._
import java.io.{FileWriter, File, BufferedWriter}

object Main {
  def run2d(): Unit = {
    var d = new Diagram2
    d.fillDimple(List(0, 0))
    d.fillDimple(List(0, 1))
    d.fillDimple(List(0, 2))
    println(d.mDimples)
    println(d.count())

    val G2 = new RichardsonsGenerator(50000, 2)
    def One(l: List[Int]): Double = {
      return 1.0
    }

    //for (i <- 0 to 100) {
      val diagram = G2.generate((l: List[Int]) => 1.0).asInstanceOf[Diagram2]
      println("" + diagram.count + "\n")
      //println(diagram.asInstanceOf[SDiagram2].mDimples)
    //}

    val bw = new BufferedWriter(new FileWriter(new File("2d.txt")));

    for (d <- diagram.getDimples()) {
      bw.write("" + math.sqrt(d(0)) + " " + math.sqrt(d(1)))
      bw.newLine();
    }

    bw.close();
  }

  def run3d(): Unit = {
    val d = new Diagram3
    d.fillDimple(List(0, 0, 0))
    d.fillDimple(List(1, 0, 0))
    d.fillDimple(List(0, 1, 0))
    d.fillDimple(List(0, 0, 1))
    println(d.getDimples())
    println(d.count())

    val G3 = new RichardsonsGenerator(1000, 3)
    def One(l: List[Int]): Double = {
      return 1.0
    }

    val diag = G3.generate(One).asInstanceOf[Diagram3]

    val bw = new BufferedWriter(new FileWriter(new File("3d.txt")));

    for (d <- diag.getDimples()) {
      bw.write("" + d(0) + " " + d(1) + " " + d(2))
      bw.newLine();
    }

    bw.close();
    println(diag.count())
  }

  def main(args: Array[String]) {
    run2d();
  }
}
