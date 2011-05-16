package vs.thesis

trait IDiagram {
  def count(): Int
  def dimension(): Int

  def getCorners(): List[List[Int]]
  def getDimples(): List[List[Int]]
  def fillDimple(dimple: List[Int]): Unit
}
