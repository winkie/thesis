package vs.thesis.visualization

import org.jzy3d.chart.controllers.mouse.ChartMouseController
import org.jzy3d.chart.Chart
import org.jzy3d.colors.Color
import javax.swing.{JComponent, JFrame}
import vs.thesis.{Diagram3, IDiagram}
import org.jzy3d.plot3d.primitives.textured.TexturedCube
import org.jzy3d.plot3d.primitives.{Point, Quad}
import org.jzy3d.maths.Coord3d

class D3Visualizer extends IVisualizer {
  val frm = new JFrame("IDiagram")
  val chart = new Chart("swing")
  chart.addController(new ChartMouseController())
  frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  frm.setSize(500, 500)

  def show(d: IDiagram) {
    val d3 = d.asInstanceOf[Diagram3]
    val color = Color.RED

    val norm = math.sqrt(d.count())
    val blocks = d3.mBlocks

    //chart.setAxeDisplayed(true)
    //chart.setViewMode(ViewPositionMode.TOP)

    val s = chart.getScene() //new Scene
    //chart.getView().getCamera().setEye(new Coord3d(0, 0, -5))
    //chart.getView().getCamera().setTarget(new Coord3d(0, 0, 1))
    //chart.getView().getCamera().setUp(new Coord3d(0, 1, 0))
    //println(chart.getView().getCamera().getEye())
    //println(chart.getView().getCamera().getTarget())
    //println(chart.getView().getCamera().getUp())

//    val b = math.max(dimples.head(1), dimples.last(0))
//    val bound = new Quad
//    bound.add(point(0, 0)); bound.add(point(0, b))
//    bound.add(point(b, b));  bound.add(point(b, 0))
//    s.add(bound)

    def cube(x: Int, y: Int, z: Int) {
      if (blocks.contains(List(x + 1, y, z)) &&
          blocks.contains(List(x, y + 1, z)) &&
          blocks.contains(List(x, y, z + 1))
        )
        return

      var q = new Quad()
      q.add(new Point(new Coord3d(x, y, z + 1)))
      q.add(new Point(new Coord3d(x, y + 1, z + 1)))
      q.add(new Point(new Coord3d(x + 1, y + 1, z + 1)))
      q.add(new Point(new Coord3d(x + 1, y, z + 1)))
      q.setColor(Color.BLUE)
      s.add(q)

      q = new Quad()
      q.add(new Point(new Coord3d(x + 1, y, z)))
      q.add(new Point(new Coord3d(x + 1, y + 1, z)))
      q.add(new Point(new Coord3d(x + 1, y + 1, z + 1)))
      q.add(new Point(new Coord3d(x + 1, y, z + 1)))
      q.setColor(Color.RED)
      s.add(q)

      q = new Quad()
      q.add(new Point(new Coord3d(x, y + 1, z)))
      q.add(new Point(new Coord3d(x, y + 1, z + 1)))
      q.add(new Point(new Coord3d(x + 1, y + 1, z + 1)))
      q.add(new Point(new Coord3d(x + 1, y + 1, z)))
      q.setColor(Color.GREEN)
      s.add(q)
    }

    blocks.foreach((l: List[Int]) => cube(l(0), l(1), l(2)))
    val m = blocks.map((l: List[Int]) => l.max).max + 1

    var q = new Quad()
    q.add(new Point(new Coord3d(0, 0, 0)))
    q.add(new Point(new Coord3d(0, m, 0)))
    q.add(new Point(new Coord3d(m, m, 0)))
    q.add(new Point(new Coord3d(m, 0, 0)))
    q.setColor(Color.GRAY)
    s.add(q)
    q = new Quad()
    q.add(new Point(new Coord3d(0, 0, 0)))
    q.add(new Point(new Coord3d(0, 0, m)))
    q.add(new Point(new Coord3d(0, m, m)))
    q.add(new Point(new Coord3d(0, m, 0)))
    q.setColor(Color.GRAY)
    s.add(q)
    q = new Quad()
    q.add(new Point(new Coord3d(0, 0, 0)))
    q.add(new Point(new Coord3d(0, 0, m)))
    q.add(new Point(new Coord3d(m, 0, m)))
    q.add(new Point(new Coord3d(m, 0, 0)))
    q.setColor(Color.GRAY)
    s.add(q)

    chart.getView.setBackgroundColor(Color.GRAY)
    frm.add(chart.getCanvas().asInstanceOf[JComponent])
    frm.setVisible(true)
  }
}

