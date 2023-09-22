package bouncingBalls;

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

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

  public void moveBall(BallFrame f, ArrayList<Ball> balls) {
      maxWidth = f.getFrameWidth() - (ballWidth);
      maxHeight = f.getFrameHeight() - (ballWidth);

      if (BallFrame.running == true) {
          // Check for collisions with the frame boundaries
          if (originX + ballWidth >= maxWidth || originX <= 0) {
              dx = -dx;
          }
          if (originY + ballWidth >= maxHeight || originY <= 0) {
              dy = -dy;
          }

          // Check for collisions with other balls
          for (Ball otherBall : balls) {
              if (otherBall != this) {
                  double otherX = otherBall.getX();
                  double otherY = otherBall.getY();
                  double distance = Math.sqrt(Math.pow(otherX - originX, 2) + Math.pow(otherY - originY, 2));

                  if (distance <= ballWidth) {
                      // Collided with another ball, reverse directions
                      dx = -dx;
                      dy = -dy;
                      otherBall.dx = -otherBall.dx;
                      otherBall.dy = -otherBall.dy;
                  }
              }
          }

          originX += dx;
          originY += dy;
      }
  }

  public void draw(Graphics2D g2) {
    ballEllipse = new Ellipse2D.Double(originX, originY, ballWidth, ballWidth);
    g2.setColor(ballColour);
    g2.fill(ballEllipse);
  }
}
