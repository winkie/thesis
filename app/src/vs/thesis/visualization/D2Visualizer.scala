package vs.thesis.visualization

import org.jzy3d.chart.Chart
import javax.swing.{JComponent, JFrame}
import org.jzy3d.chart.controllers.mouse.ChartMouseController
import org.jzy3d.maths.Coord3d
import org.jzy3d.colors.Color
import org.jzy3d.plot3d.primitives.{Polygon, Point, Quad}
import org.jzy3d.plot3d.rendering.view.modes.ViewPositionMode

class D2Visualizer extends IVisualizer {
  val frm = new JFrame("IDiagram")
  val chart = new Chart("swing")
  chart.addController(new ChartMouseController())
  frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  frm.setSize(500, 500)

  def show(d: IDiagram) {
    val color = Color.RED

    val norm = math.sqrt(d.count())
    val dimples = d.getDimples().reverse.map((l: List[Int]) => 
      List(l(0) / norm, l(1) / norm))
    val p = new Polygon

    chart.setAxeDisplayed(true)
    chart.setViewMode(ViewPositionMode.TOP)

    val s = chart.getScene() //new Scene
    //chart.getView().getCamera().setEye(new Coord3d(0, 0, -5))
    //chart.getView().getCamera().setTarget(new Coord3d(0, 0, 1))
    //chart.getView().getCamera().setUp(new Coord3d(0, 1, 0))

    println(chart.getView().getCamera().getEye())
    println(chart.getView().getCamera().getTarget())
    println(chart.getView().getCamera().getUp())
    def point(x: Double, y: Double): Point = {
      return new Point(new Coord3d(x, y, 0));
    }

    p.add(new Point(new Coord3d(0, 0, 0)))

    val b = math.max(dimples.head(1), dimples.last(0))
    val bound = new Quad
    bound.add(point(0, 0)); bound.add(point(0, b))
    bound.add(point(b, b));  bound.add(point(b, 0))
    s.add(bound)

    dimples.dropRight(1).zip(dimples.drop(1))
      .foreach((coords: (List[Double], List[Double])) => {
        val x1 = coords._1(0); val y1 = coords._1(1)
        val x2 = coords._2(0); val y2 = coords._2(1)

        p.add(point(x1, y1))
        p.add(point(x2, y1))
    })
    p.add(point(dimples.last(0), 0))
    p.setColor(color)
    s.add(p)

    //chart.getScene.setGraph(s.getGraph())

    frm.add(chart.getCanvas().asInstanceOf[JComponent])
    frm.setVisible(true)
  }
}

