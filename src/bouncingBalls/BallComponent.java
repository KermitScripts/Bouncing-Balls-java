package bouncingBalls;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.JComponent;

class BallComponent extends JComponent {

  public Graphics2D g2;
  private ArrayList<Ball> ballArrayList = new ArrayList<Ball>();

  // Override paintComponent method of parent class
  public void paintComponent(Graphics g) {
    g2 = (Graphics2D) g;
    for (int i = 0; i<ballArrayList.size(); i++) {
      ballArrayList.get(i).draw(g2);
    }
  }

  public  void printArray() {
    for (int i = 0; i<ballArrayList.size(); i++) {
      System.out.println(ballArrayList.get(i));
    }
  }
  public ArrayList getArray() {
    return ballArrayList;
  }

  public void removeBall(Ball b) {
    ballArrayList.remove(b);
  }

  public void addBall(Ball b) {
    ballArrayList.add(b);
  }

  public void clearArray() {
    ballArrayList.clear();
  }

  public void paintBalls() {
    repaint();
  }
}
