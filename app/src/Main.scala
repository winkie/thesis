
object Main {
  def run2d(): Unit = {
    var d = new Diagram2
    d.fillDimple(List(0, 0))
    d.fillDimple(List(0, 1))
    d.fillDimple(List(0, 2))
    println(d.mDimples)
    println(d.count())

    val G2 = new RichardsonsGenerator(10000, 2)
    def One(l: List[Int]): Double = {
      return 1.0
    }

    for (i <- 0 to 100) {
      val diagram = G2.generate(One)
      println("" + diagram.count + "\n")
      //println(diagram.asInstanceOf[SDiagram2].mDimples)
    }
  }

  def run3d(): Unit = {
    val d = new Diagram3
    d.fillDimple(List(0, 0, 0))
    d.fillDimple(List(1, 0, 0))
    d.fillDimple(List(0, 1, 0))
    d.fillDimple(List(0, 0, 1))
    println(d.getDimples())
    println(d.count())

    val G3 = new RichardsonsGenerator(1000, 2)
    def One(l: List[Int]): Double = {
      return 1.0
    }

    val diag = G3.generate(One)

    println(diag.count())
  }

  def main(args: Array[String]) {
    run3d();
  }
}
