package bouncingBalls;

import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class BallViewer {
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new BallFrame();
        frame.setTitle("Ball Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
      }
    });
  }
}
