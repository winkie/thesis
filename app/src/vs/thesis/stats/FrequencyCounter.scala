package vs.thesis.stats

import vs.thesis.{IDiagramEnumerator, IGenerator}

class FrequencyCounter(size: Int, generator: IGenerator,
                       weight: (List[Int]) => Double,
                       enumerator: IDiagramEnumerator) {
  def getFrequencies(intervals: Int, runs: Int): Array[Int] = {
    val count = enumerator.count(size)
    println("Count = " + count)
    val binSize = count / intervals
    println(binSize)
    val histogram = Array.fill(intervals){0}

    print("Run: ")
    for (i <- 0 until runs) {
      if (i % 1000 == 0) println(i)
      val d = generator.generate(weight, size)
      val num = enumerator.diagramToNumber(d)
      var bin = num / binSize
      if (bin >= intervals)
        bin = intervals - 1

      assert(bin.isValidInt)

      //println("Num = " + num + "; Bin = " + bin)
      histogram(bin.toInt) += 1
    }

    return histogram
  }
}