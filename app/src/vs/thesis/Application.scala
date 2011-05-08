package vs.thesis

import javax.swing.JFrame

object Application {
  def main(args: Array[String]) = {
    val x = new JFrame("Ololol")
    x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    x.setSize(300, 300)
    x.setVisible(true)
  }
}