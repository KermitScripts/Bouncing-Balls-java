package bouncingBalls;

import java.util.ArrayList;

public class BallRunnable implements Runnable {
    private Ball ball;
    private BallComponent component;
    private BallFrame frame;
    private int speed;
    private ArrayList<Ball> balls; // Add a list to hold all balls

    public BallRunnable(Ball b, BallComponent c, BallFrame f, ArrayList<Ball> balls) {
        ball = b;
        component = c;
        frame = f;
        this.balls = balls; // Store the list of balls
    }

    public void run() {
        try {
            while (frame.getstopThreads() == false) {
                speed = frame.setSpeed();
                ball.moveBall(frame, balls);
                component.paintBalls();
                Thread.sleep(speed);
            }
        } catch (InterruptedException e) {
        }
    }
}
