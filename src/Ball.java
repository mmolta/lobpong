/*
 * Marc Molta
 * CSC 171
 * Lab: Monday Wednesday 2-315
 * Ta: Wallis 
 * I certify this work is my own 
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;

public class Ball {
	public int x, y, width, height;
	public int vox = 10, voy = 10;
	public double g, xSpeed, ySpeed;
	public double xDir = vox*Math.cos(Math.toRadians(32))*2;
	public double yDir = voy*Math.sin(Math.toRadians(32))* 2 - (g*4)/2;

	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void velocity(int v){
		xSpeed = v*Math.cos(Math.toRadians(45));
	}
	
	public void setInitialSpeed(int v){
		this.vox = v;
		this.voy = v;
	}
	
	public void setGravity(double g){
		this.g = g;
	}
	
	public double getGravity(){
		return g;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public double getX(){
		return x;
	}
	
	public void move(){
		// direction in which the ball is going

		//System.out.println(yDir);
		//System.out.println(xDir);
		
		x += xDir;
		y -= yDir;
		
		// bounce left from the right boundary
		if(x > 500 - getWidth()){
			xDir = xDir * -1;
		}
		// bounce from top
		else if(y < 0){
			yDir = yDir * -1;
		}
		// bounce right from the left boundary
		else if(x < 15){
			xDir = xDir *-1;
		
		// bounce off the floor
		}else if(y > 450 - getHeight()){
			//System.out.println("bar");
			yDir = -yDir;
		}
	}
	
	public void draw(Graphics g){
		width = height = 15;
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);
	}
}
