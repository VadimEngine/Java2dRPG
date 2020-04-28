package entites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import art.Art;

public class Bush {
	
	private int x;
	private int y;
	private int width = 48;
	private int height = 48;
	private int imageWidth = 64;
	private BufferedImage image = Art.SPRITES[5][0];
	private BufferedImage imagePicked = Art.SPRITES[5][1];
	private BufferedImage imageChopped = Art.SPRITES[5][2];
	private boolean chopped;
	private boolean picked;
	private int timer1 = 0;
	private int timer2 = 0;
	
	public Bush(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if (timer1 > 1000) {
			timer1 = 0;
			chopped = false;
		}
		if (chopped) {
			timer1++;
		}
		
		if (timer2 > 1000) {
			timer2 = 0;
			picked = false;		
		}
		if (!chopped && picked) {
			timer2++;
		}
		
	}
	
	public void render(Graphics g) {
		
		if (!chopped && !picked) {
			g.drawImage(image, x, y, imageWidth, imageWidth, null);
		}
		if (!chopped && picked) {
			g.drawImage(imagePicked, x, y, imageWidth, imageWidth, null);
		}
		if (chopped) {
			g.drawImage(imageChopped, x, y, imageWidth, imageWidth, null);
		}
		//g.drawRect(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void chop() {
		chopped = true;
	}
	
	public void pick() {
		picked = true;
	}
	
	public boolean hasBerries() {
		return (!chopped && !picked);
	}
	
	public boolean isGrown() {
		return !chopped;
	}

}
