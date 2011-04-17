class Diagram2 extends Diagram {
  def count() = mCount
    /*
  :Int = {
    if (mDimples.size == 1)
      return mDimples(0)(0)

    var count: Int = 0
    for (i <- mDimples.size - 1 to 1) {
      val d1 = mDimples(i)
      val d2 = mDimples(i - 1)
      count += (d1(1) - d2(1)) * d2(0)
    }
    return count
  }
  */
  def dimension() = 2

  def getDimples(): List[List[Int]] = {
    return mDimples;
  }

  def fillDimple(dimple: List[Int]): Unit = {
    val ind = mDimples.findIndexOf(_ == dimple)

    val up = (ind == mDimples.size - 1 || mDimples(ind + 1)(1) - dimple(1) > 1)
    val side = (ind == 0 || mDimples(ind - 1)(0) - dimple(0) > 1)

    val head = mDimples.slice(0, ind);
    val tail = mDimples.slice(ind + 1, mDimples.size)

    if (up && side) {
      mDimples = head ::: List(List(dimple(0) + 1, dimple(1))) :::
        List(List(dimple(0), dimple(1) + 1)) ::: tail
    } else if (up) {
      mDimples = head ::: List(List(dimple(0), dimple(1) + 1)) ::: tail
    } else if (side) {
      mDimples = head ::: List(List(dimple(0) + 1, dimple(1))) ::: tail
    } else {
      mDimples = head ::: tail
    }
    mDimples = mDimples.filterNot(_ == List())
    mCount = mCount + 1
  }

  var mDimples = List(List(0, 0))
  var mCount = 0
}