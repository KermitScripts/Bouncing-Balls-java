package bouncingBalls;

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.JComponent;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallFrame extends JFrame implements ActionListener, ChangeListener {

  final static int FRAME_WIDTH = 600;
  final static int FRAME_HEIGHT = 600;
  final static int BALL_FRAME_WIDTH = 600;
  final static int BALL_FRAME_HEIGHT = 500;
  final static int BALL_SIZE = 20;

  public static boolean running = true;
  private boolean stopThreads = false;
  private JButton startButton, pauseButton, stopButton;
  private BallComponent component;
  private JSlider speedSlider;
  private Random generator;
  private int randomNumber, randomX, randomY, sliderValue;
  private Color randomColour;
  private int speed = 6;

  public BallFrame() {
    component = new BallComponent();
    component.setPreferredSize(new Dimension(BALL_FRAME_WIDTH, BALL_FRAME_HEIGHT));
    createBallPanel();
    createButtons();
    createSlider();
    createdisplayPanel();
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
  }

  public double getFrameWidth() {
    return BALL_FRAME_WIDTH;
  }

  public double getFrameHeight() {
    return BALL_FRAME_HEIGHT;
  }

  private void createButtons() {

    startButton = new JButton("Start");
    startButton.addActionListener(this);

    pauseButton = new JButton("Pause");
    pauseButton.addActionListener(this);

    stopButton = new JButton("Stop");
    stopButton.addActionListener(this);
  }


  public void actionPerformed(ActionEvent event) {
    if (event.getSource( ) == startButton) {
      System.out.println("Start pressed");
      createBall();
    }
    else if (event.getSource( ) == pauseButton) {
      System.out.println("Pause pressed");
      paused();
    }
    else if (event.getSource( ) == stopButton) {
      System.out.println("Stop pressed");
      stop();
    }
  }

  private void createSlider() {
    speedSlider = new JSlider(1, 5, 3);
    speedSlider.setMajorTickSpacing(1);
    speedSlider.setPaintTicks(true);
    speedSlider.setPaintLabels(true);
    speedSlider.addChangeListener(this);
  }

  public void stateChanged(ChangeEvent event) {
    if (event.getSource( ).equals(speedSlider)) {
       sliderValue = speedSlider.getValue();
    }
  }

  private void createdisplayPanel() {
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    buttonPanel.setLayout(new GridLayout(1, 3));
    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(stopButton);
    displayPanel.add(buttonPanel);
    displayPanel.add(speedSlider);

    add(displayPanel, BorderLayout.SOUTH);
  }

  //creates a Jpanel for the ball component
  private void createBallPanel() {
    JPanel ballPanel = new JPanel();
    ballPanel.add(component);
    add(ballPanel, BorderLayout.NORTH);
  }

  //Code for creating a Ball and a thread
  private void createBall() {
      randomX = randomNumber(BALL_FRAME_WIDTH);
      randomY = randomNumber(BALL_FRAME_HEIGHT);
      Ball b = new Ball(randomX, randomY, BALL_SIZE, randomColour());
      component.addBall(b);
      ArrayList<Ball> balls = component.getArray(); 
      Runnable r = new BallRunnable(b, component, this, balls); 
      component.repaint();
      Thread t = new Thread(r);
      t.start();
  }
  
  public boolean getstopThreads() {
	  return stopThreads;
  }

  public void setstopThreads(boolean b) {
	  stopThreads = b;
  }

  public void paused() {
	  if (running == true) {
		  running = false;
		  pauseButton.setText("Resume");
	  }
	  else {
		  running = true;
		  pauseButton.setText("Pause");
	  }
  }

  public void stop() {
    stopThreads = true;
    try {
      Thread.currentThread();
      Thread.sleep(5000);
    }
    catch (InterruptedException e) {
    }
    component.clearArray();
    component.repaint();
    stopThreads = false;
  }

  public int setSpeed() {
    if (sliderValue == 1) {
      speed = 10;
    }
    else if (sliderValue == 2) {
      speed = 8;
    }
    else if (sliderValue == 3) {
      speed = 6;
    }
    else if (sliderValue == 4) {
      speed = 4;
    }
    else if (sliderValue == 5) {
      speed = 2;
    }
    return speed;
  }

  public int randomNumber(int m) {
    generator = new Random();
    randomNumber = 1 + generator.nextInt(m);
    return randomNumber;
  }
  
  public Color randomColour() {
    generator = new Random();
    randomColour = new Color((int)(Math.random() * 0x1000000));
    return randomColour;
  }
}
