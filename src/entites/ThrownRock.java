package entites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import art.Art;

public class ThrownRock {

	private int x;
	private int y;
	private int dir;
	private boolean dropped;
	private int width = 32;
	
	private int speed = 10;
	private int time = 0;
	private BufferedImage image = Art.SPRITES[2][0];

	public ThrownRock(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void render(Graphics g) {
		g.drawImage(image, x, y, width, width, null);
	}

	public void tick() {
		if (time <= 30) {
			if (dir == 0) {
				x-= speed;
			} else if (dir == 1) {
				x+= speed;
			} else if (dir == 2) {
				y+= speed;
			} else if (dir == 3) {
				y-= speed;
			}
			time++;
		} else {
			dropped = true;
		}
	}

	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isDropped() {
		return dropped;
	}
	
	public int getWidth() {
		return width;
	}



}
