package vs.thesis

import collection.mutable.HashSet

class Diagram3 extends Diagram {
  def count() = mCount

  def dimension() = 3

  def getDimples(): List[List[Int]] = {
    return mDimples.toList;
  }

  private def block(b: List[Int]): Boolean = {
    if (b(0) < 0 || b(1) < 0 || b(2) < 0)
      return true;

    return mBlocks.contains(b);
  }

  def fillDimple(dimple: List[Int]): Unit = {

    val up = List(dimple(0), dimple(1), dimple(2) + 1)
    val lside = List(dimple(0), dimple(1) + 1, dimple(2))
    val rside = List(dimple(0) + 1, dimple(1), dimple(2))

    val addUp = block(List(up(0), up(1) - 1, up(2))) && block(List(up(0) - 1, up(1), up(2)))
    val addLSide = block(List(lside(0) - 1, lside(1), lside(2))) && block(List(lside(0), lside(1), lside(2) - 1))
    val addRSide = block(List(rside(0), rside(1) - 1, rside(2))) && block(List(rside(0), rside(1), rside(2) - 1))

    mDimples -= dimple
    mBlocks += dimple

    if (addUp)
      mDimples += up
    if (addLSide)
      mDimples += lside
    if (addRSide)
      mDimples += rside

    mCount = mCount + 1
  }

  var mDimples = HashSet[List[Int]](List(0, 0, 0))
  var mBlocks = HashSet[List[Int]]()
  var mCount = 0
}