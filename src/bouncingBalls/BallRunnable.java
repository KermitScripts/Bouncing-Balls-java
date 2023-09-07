package bouncingBalls;

public class BallRunnable implements Runnable
{
  private Ball ball;
  private BallComponent component;
  private BallFrame frame;
  private int speed;


   public BallRunnable(Ball b, BallComponent c, BallFrame f) {
      ball = b;
      component = c;
      frame = f;
   }

  public void run() {
  		try {
        while (frame.getstopThreads() == false) {
          speed = frame.setSpeed();
          ball.moveBall(frame);
          component.paintBalls();
          Thread.sleep(speed);
        }
  		}
  		catch (InterruptedException e){
    }
  }
}

