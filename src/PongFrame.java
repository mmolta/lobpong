import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * Marc Molta
 * CSC 171
 * Lab: Monday Wednesday 2-315
 * Ta: Wallis 
 * I certify this work is my own 
 */

public class PongFrame extends JFrame {
	
	// graphics variables
	protected int width = 500;
	protected int height = 500;
	protected int barHeight = 450;
	protected int gameLevel, nextLevel = 1, levelTime = 8000;
	protected Image background;	
	protected JLabel score, level, lives, time;
	

	protected JTextField gScore, gLevel, gLives;
	// integer values to pass thru the JTtextFields 
	protected int gScoreNum = 0, gLevelNum = 1, gLivesNum = 3;
	protected PongCanvas2 canvas;
	protected PongGUI gui;
	LobBar bar = new LobBar();
	Ball ball = new Ball();
	
	// animation variables 
	protected Timer timer;
	protected int timerDelay = 100;
	protected int gameDelay = 50;
	// starting spot for the bar 
	int barLoc = width/2 - bar.getWidth();
	int ballX = 0;
	int ballY = 250;
	
	// modeled my constructor after the Super Giorgio code 
	public PongFrame(){
		addMedium();
		addTimer();
		addKeyHandler();
		addBar();
		addBall();
		addNextLevel();
	}
	
	protected class PongCanvas2 extends JPanel{
		Image level1 = loadImage("graphics/level1.jpg");
		Image level2 = loadImage("graphics/level2.jpg");
		Image level3 = loadImage("graphics/level3.jpg");
		Image level4 = loadImage("graphics/level4.png");
		
		protected Image loadImage(String imagename){
			try{
				return ImageIO.read(new File(imagename));
			} catch(IOException e){
				System.err.println(e);
				return null;
			}
		}
		
		public PongCanvas2(int level){
			setPreferredSize(new Dimension(width, height));
			gameLevel = level;
			setFocusable(true);
		}

		public void setLevel(int level){
			gameLevel = level;
		}
		
		public int getLevel(){
			return gameLevel;
		}
		
		// extra credit. each level has a different background 
		public void drawBackground(Graphics g){
			if(gameLevel == 1){
				g.drawImage(level1, 0, 0, null);
			}else if(gameLevel == 2){
				g.drawImage(level2, 0, 0, null);
			}else if(gameLevel == 3){
				g.drawImage(level3, 0, 0, null);
			}else if(gameLevel == 4){
				g.drawImage(level4, 0, 0, null);
			}
		}
		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawBackground(g);
			bar.draw(g);
			ball.draw(g);
		}
	}
	
	protected class PongGUI extends JPanel{
		// adds the GUI for scores, lives and timer 
		JPanel pongGUI = new JPanel();
		
		public PongGUI(){
			pongGUI.setLayout(new FlowLayout());
			
			score = new JLabel("Score: ");
			level = new JLabel("Level: ");
			lives = new JLabel("Lives: ");
			//time = new JLabel("Time: ");
			
			gScore = new JTextField(Integer.toString(gScoreNum));
			gScore.setPreferredSize(new Dimension(30, 20));
			
			gLives = new JTextField(Integer.toString(gLivesNum));
			gLives.setPreferredSize(new Dimension(30, 20));
			
			gLevel = new JTextField(Integer.toString(gLevelNum));
			gLevel.setPreferredSize(new Dimension(30, 20));
				
			pongGUI.add(score);
			pongGUI.add(gScore);
			pongGUI.add(level);
			pongGUI.add(gLevel);
			pongGUI.add(lives);
			pongGUI.add(gLives);
			//pongGUI.add(time);
			
			
			add(pongGUI, BorderLayout.NORTH);
		}
	}

	public void addMedium(){
		setTitle("ULTIMATE PONG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui = new PongGUI();
		canvas = new PongCanvas2(1);
		canvas.setPreferredSize(new Dimension(width, height));
		add(canvas, BorderLayout.CENTER);
		add(gui, BorderLayout.NORTH);
		pack();
	}

	public void addBar(){
		bar = new LobBar();
		bar.setLocation(barLoc);
	}
	
	public void addBall(){
		ball = new Ball();
		ball.setLocation(ballX, ballY);
		ball.setInitialSpeed(10);
		ball.setGravity(10);
	}
	
	
	protected void addTimer(){
		timer = new Timer(timerDelay, new TimerRunner());
	}
	
	// Inner Timer class adapted from Super Giorgio 
	protected class TimerRunner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			bar.change();
			ball.move();
			canvas.repaint();
			
			/* The gampeplay is here. Not sure if it's the best spot, but
			 it works
			*/ 
			
			/* whenever the ball has the same y as the bar, and has x in the same
			 range as the width of the bar, that means it's hit the bar and
			 the player gets a point
			*/
			if(ball.getY() > 450 - ball.getHeight() && bar.getX() < ball.getX() && ball.getX() < bar.getLocation()){
				//System.out.println("Score!");
				gScoreNum += 1;
				gScore.setText(Integer.toString(gScoreNum));
				
			// resets for a miss
			}else if(ball.getY() > 450 - ball.getHeight()){
				ball.setX(0);
				ball.setY(250);
				gLivesNum -= 1;
				gLives.setText(Integer.toString(gLivesNum));
				if(gLivesNum == 0){
					timer.stop();
					System.out.println("Game Over");
				}
			}
		}
	}
		// the duration of the following level will be 2/3 as long as the 
		// previous level 
		// levelTime *= 2/3;

	public void addNextLevel(){
		if(canvas.getLevel() == 2){
			gLevel.setText("2");
			levelTime *= 2/3;
			ball.setGravity(ball.getGravity()*(3));
		}else if(canvas.getLevel() == 3){
			gLevel.setText("3");
			levelTime *= 4/7;
			ball.setGravity(ball.getGravity()*(1/9));
		}else if(canvas.getLevel() == 4){
			gLevel.setText("4");
			levelTime *= 5/8;
			// random gravity for the final level 
			ball.setGravity(ball.getGravity()*(8*Math.random()));
		}
		// Level timer adapted from Stack Overflow
		// http://stackoverflow.com/questions/2258066/java-run-a-function-after-a-specific-number-of-seconds
			new java.util.Timer().schedule(
					new java.util.TimerTask(){
						@Override
						public void run(){
							canvas.setLevel(nextLevel);
						}
					}, levelTime);
			
			// initializes the new level, increases the duration of the level and increases 
			// the effect of gravity 
			//ball.setX(0);
			//ball.setY(250);
			// levelTime *= 2/3;
			nextLevel += 1;
	}
	
	public void addKeyHandler(){
		canvas.addKeyListener(new KeyHandler());
		//System.out.println("print something");
	}	
	
	// modeled after Super Giorgio
	protected class KeyHandler implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			/*if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				bar.setHorizontalSpeed(+10);
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				bar.setHorizontalSpeed(-10);;
			}*/
			switch(e.getKeyCode()){
				case KeyEvent.VK_RIGHT:
					//System.out.println("something else");
					barLoc += 10;
					//bar.setLocation(barLoc);
					bar.setHorizontalSpeed(+15);
					if(barLoc > 500){
						barLoc = 500;
					}
					break;
				case KeyEvent.VK_LEFT:
					bar.setHorizontalSpeed(-15);
					if(barLoc < 0){
						barLoc = 0;
					}
					break;
				case KeyEvent.VK_SPACE:
					ball.move();
					//ball.setLocation(250, 435);
			}
			
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()){
				case KeyEvent.VK_RIGHT:
					// these two lines might do the same thing
					bar.setHorizontalSpeed(0);
					barLoc += 0;
				case KeyEvent.VK_LEFT:
					bar.setHorizontalSpeed(0);
					barLoc += 0;
				case KeyEvent.VK_SPACE:
					ball.move();
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
	
	public void startGame(){
		setVisible(true);
		timer.start();
	}
	
	public static void main(String[] args){
		new PongFrame().startGame();
	}

}
