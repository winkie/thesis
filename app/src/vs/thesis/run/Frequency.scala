package vs.thesis.run

import vs.thesis.stats.FrequencyCounter
import vs.thesis.{Simple2DEnumerator, RichardsonsGenerator}
import java.io.{BufferedWriter, FileWriter, File}

object Frequency {
  def main(args: Array[String]) {
    val N = 1000
    val generator = new RichardsonsGenerator(2)
    val enumerator = new Simple2DEnumerator(N)
    val weightUniform = (l: List[Int]) => math.pow(math.pow(l(0), 2) + math.pow(l(1), 2), 0.16)
    val weightOne = (l: List[Int]) => 1.0
    val freqs = new FrequencyCounter(N, generator, weightUniform, enumerator)

    val intervals = 1000
    var histogram = freqs.getFrequencies(intervals, 15000)
    val bw = new BufferedWriter(new FileWriter(new File("histogram6.txt")));

    histogram = histogram.sortWith(_ <= _)

    for (i <- 0 until intervals) {
      bw.write("" + i + " " + histogram(i))
      bw.newLine();
    }

    bw.close();
  }
}