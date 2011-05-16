package vs.thesis

trait IGenerator {
  def generate(weight: List[Int] => Double): IDiagram
}
