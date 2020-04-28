package testingEnt;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import art.Art;
import other.Animation;


/**
 * @author user
 *
 */
public class Chicken extends AbstractEntity{

	private BufferedImage image;
	private int oldX;
	private int oldY;
	
	private int dir;
	private int speed = 5;

	/**
	 * up[0]
	 * down[1]
	 * left[2]
	 * right[3]
	 * @param x
	 * @param y
	 * @param handler
	 * @param entities
	 */
	public Chicken(int x, int y, List<AbstractEntity> entities) {
		super(x, y, new Animation(Art.SPRITES[4][9],Art.SPRITES[4][10],Art.SPRITES[4][11]),
				new Animation(Art.SPRITES[4][6],Art.SPRITES[4][7],Art.SPRITES[4][8]),
				new Animation(Art.SPRITES[4][0],Art.SPRITES[4][1],Art.SPRITES[4][2]),
				new Animation(Art.SPRITES[4][3],Art.SPRITES[4][4],Art.SPRITES[4][5]));
		this.width = 32;
		this.height = 32;
		oldX = x;
		oldY = y;
		dir = random.nextInt(5);
	}

	@Override
	public void tick() {

		oldX = x;
		oldY = y;

		//blocks from leaving map, replace with solid blocks outside map
		if (x < 0) {
			x = 0;
		}
		if (x > 64 * 31) {
			x = 64 * 31;
		}
		if (y < 0) {
			y = 0;
		}
		if (y > 58 * 32) {
			y = 32 * 58;
		}
		//blocks from leaving map
		
		AI();
		
		for (int i = 0; i < animations.length; i++) {
			animations[i].runAnimation();
		}

	}
	@Override
	public void render(Graphics g) {
		if (oldX < x) {//moveright
			animations[3].drawAnimation(g, x, y, width);
			image = animations[3].getImage(0);
		} else if (oldX > x) {//moveleft
			animations[2].drawAnimation(g, x, y, width);
			image = animations[2].getImage(0);
		} else if (oldY < y) {//movedown
			animations[1].drawAnimation(g, x, y ,width);
			image = animations[1].getImage(0);
		} else if (oldY > y) {//moveup
			animations[0].drawAnimation(g, x, y, width);
			image = animations[0].getImage(0);
		} else {
			g.drawImage(image, x, y, width, height, null);
		}
	}


	public void die() {

	}
	
	private void AI() {
		if (random.nextInt(100)==0) {
			dir = random.nextInt(5);
		}
		
		switch(dir) {
		case 0:
			y -= speed;
			break;
		case 1:
			y += speed;
			break;
		case 2:
			x -= speed;
			break;
		case 3:
			x += speed;
			break;
		}
		
	}

}
