import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PongCanvas extends JPanel{
	protected Image background;
	protected int width = 500;
	protected int height = 500;
	protected int level;
	LobBar bar = new LobBar();
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
	
	public PongCanvas(int level){
		// setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		this.level = level;
	}

	public void setLevel(int level){
		this.level = level;
	}
	
	public void addBar(LobBar bar){
		this.bar = bar;
	}
	
	public void drawBackground(Graphics g){
		if(level == 1){
			g.drawImage(level1, 0, 0, null);
		}else if(level == 2){
			g.drawImage(level2, 0, 0, null);
		}else if(level == 3){
			g.drawImage(level3, 0, 0, null);
		}else if(level == 4){
			g.drawImage(level4, 0, 0, null);
		}
	}

	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		height = getHeight();
		width = getWidth();
		drawBackground(g);
		bar.draw(g);
	}
}
