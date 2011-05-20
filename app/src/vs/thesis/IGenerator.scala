package vs.thesis

trait IGenerator {
  def generate(weight: List[Int] => Double, size: Int): IDiagram
}
