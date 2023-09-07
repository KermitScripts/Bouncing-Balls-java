package bouncingBalls;

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.geom.*;

public class Ball {

  private Ellipse2D.Double ballEllipse;
  private Color ballColour;
  private double maxWidth;
  private double maxHeight;
  private double originX;
  private double originY;
  private double ballWidth;
  private double dx = 1;
  private double dy = 1;

  public Ball(int x, int y, double w, Color c) {
    originX = x;
    originY = y;
    ballWidth = w;
    ballColour = c;
  }
  
  public int getX(){
    return (int) originX;
  }
  public int getY(){
    return (int) originY;
  }

  public void moveBall(BallFrame f) {
    maxWidth = f.getFrameWidth()-(ballWidth);
    maxHeight = f.getFrameHeight()-(ballWidth);

    if (BallFrame.running == true){
      if (originX == maxWidth || originX == 0) {
        dx = -dx;
      }
      if (originY == 0 || originY == maxHeight) {
        dy = -dy;
      }
      originX += + dx;
      originY += + dy;
    }
  }

  public void draw(Graphics2D g2) {
    ballEllipse = new Ellipse2D.Double(originX, originY, ballWidth, ballWidth);
    g2.setColor(ballColour);
    g2.fill(ballEllipse);
  }
}
