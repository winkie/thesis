package vs.thesis

import java.math.BigInteger

trait IDiagramEnumerator {
  def count(n: Int): BigInt
  def diagramToNumber(diagram: IDiagram): BigInt
  def numberToDiagram(number: BigInt): IDiagram
}

class Simple2DEnumerator(count: Int) extends IDiagramEnumerator {
  if (count == null || count == 0)
    throw new RuntimeException("Count is not set")

  var (mn, mx) = (0, 0)
  var arr = Array.fill(1, 1){BigInt(0)}

  def calcP(n: Int, x: Int) {
    val narr = Array.fill(n + 1, x + 1){BigInt(0)}

    for (i <- 0 to mn) {
      for (j <- 0 to mx) {
        narr(i)(j) = arr(i)(j)
      }
    }

    for (i <- mx + 1 to x) { narr(0)(i) = BigInt(1) }

    for (i <- 1 to n) {
      val s = if (i <= mn) mx + 1 else 1
      for (j <- s to x) {
        narr(i)(j) = BigInt(0)
        if (i - j >= 0)
          narr(i)(j) = narr(i - j)(j)
        narr(i)(j) += narr(i)(j - 1)

      }
    }

    arr = narr
    mn = n
    mx = x
  }

  def PP(n: Int, x: Int): BigInt = {
    if (n > mn || x > mx)
      calcP(n, x)
    return arr(n)(x);
  }

  def count(n: Int) = PP(n, n)

  def diagramToNumber(diagram: IDiagram): BigInt = {
    val diagram2 = diagram.asInstanceOf[Diagram2]
    val partition = diagram2.getPartition()
    //println(partition)

    var s = partition.sum

    assert(s == count)

    var n = BigInt(0)

    partition.foreach((i: Int) => {
      n += PP(s, i - 1)
      //println("" + n + " ||| " + s)
      s -= i
    })

    return n
  }

  def numberToDiagram(number: BigInt): IDiagram = {
    //List(100, 70, 5, 4, 2)
    var diag = List[Int]()
    var n = count
    var num = number
    while (n > 0) {
      var x = n; val i = x;
      //TODO: binSearch here
      while (num - PP(i, x - 1) < BigInt(0)) {
        x -= 1
      }

      diag = diag ::: List(x)
      num -= PP(i, x - 1)
      n -= x
    }

    print("lol: ")
    println(diag)

    return Diagram2(diag)
  }
}