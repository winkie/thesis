package vs.thesis

trait Diagram {
  def count(): Int
  def dimension(): Int

  def getDimples(): List[List[Int]]
  def fillDimple(dimple: List[Int]): Unit
}