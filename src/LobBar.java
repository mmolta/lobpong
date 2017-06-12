/*
 * Marc Molta
 * CSC 171
 * Lab: Monday Wednesday 2-315
 * Ta: Wallis 
 * This class represents a person  
 * I certify this work is my own 
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LobBar {
	Image slider = loadImage("graphics/slider.png");

	protected int xCenter;
	protected int y = 450;
	
	// bar dimensions
	protected int width = 40;
	protected int height = 8;
	
	// speed & direction of bar 
	protected int xSpeed = 0;	
		
	// only need to set X since y is fixed 
	public void setLocation(int x){
		xCenter = x;
	}
	
	// this isn't location so much as the right bound of the bar 
	public int getLocation(){
		int right = xCenter + width*2;
		return right;
	}
	
	public int getX(){
		return xCenter;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	protected void setHorizontalSpeed(int speed){
		xSpeed = speed;
	}	
	
	protected Image loadImage(String imagename){
		try{
			return ImageIO.read(new File(imagename));
		}catch (IOException e){
			System.err.println(e);
			return null;
		}
	}
	
	
	public void draw(Graphics g){
		g.drawImage(slider, xCenter, y, null);
	}
	
	// adapted from the Update method in Super Giorgio 
	public void change(){
		int rightBound = 500 - width*2;
		
		xCenter += xSpeed;
		
		if(xCenter < 0){
			xCenter = 0;
		}
		if(xCenter > rightBound){
			xCenter = rightBound;
		}		
	}
	
}
