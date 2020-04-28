package entites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import art.Art;
import other.Animation;

public class TempFire {

	private int x;
	private int y;
	private BufferedImage image1 = Art.SPRITES[5][3];
	private BufferedImage image2 = Art.SPRITES[5][4];
	private BufferedImage image3 = Art.SPRITES[5][5];
	
	private Animation animation = new Animation(image1, image2, image3);
	private int width = 64;

	public TempFire(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick() {
		animation.runAnimation();
	}

	public void render(Graphics g) {
		animation.drawAnimation(g, x, y);
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
}
