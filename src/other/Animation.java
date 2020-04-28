package other;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	//width field

	private int speed = 10;
	private int frames = 0;

	private int index = 0;
	private int count = 0;

	private BufferedImage[] images;
	private BufferedImage currentImg;

	public Animation(BufferedImage...args) {
		images = new BufferedImage[args.length];
		for (int i = 0; i < args.length; i++) {
			images[i] = args[i];
		}
		frames = args.length;
	}

	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}

	private void nextFrame() {
		for (int i = 0; i < frames; i++) {
			if (count == i) {
				currentImg = images[i];
			}

		}
		count++;

		if (count >= frames) {
			count = 0;
		}


	}

	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImg, x, y, 64, 64, null);
	}
	
	public void drawAnimation(Graphics g, int x, int y, int width) {
		g.drawImage(currentImg, x, y, width, width, null);
	}
	
	public BufferedImage getImage(int index) {//throw oob expection?
		return images[index];
	}

}
