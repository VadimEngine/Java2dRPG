package entites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import art.Art;
import other.Animation;
import states.PlayState;

public class Chicken {

	//animation
	private int x;
	private int y;
	private int speed = 2;
	private int width = 32;
	//private int health;
	private BufferedImage image;
	
	private BufferedImage imageL1 = Art.SPRITES[4][0];
	private BufferedImage imageL2 = Art.SPRITES[4][1];
	private BufferedImage imageL3 = Art.SPRITES[4][2];
	
	private BufferedImage imageR1 = Art.SPRITES[4][3];
	private BufferedImage imageR2 = Art.SPRITES[4][4];
	private BufferedImage imageR3 = Art.SPRITES[4][5];
	
	private BufferedImage imageD1 = Art.SPRITES[4][6];
	private BufferedImage imageD2 = Art.SPRITES[4][7];
	private BufferedImage imageD3 = Art.SPRITES[4][8];
	
	private BufferedImage imageU1 = Art.SPRITES[4][9];
	private BufferedImage imageU2 = Art.SPRITES[4][10];
	private BufferedImage imageU3 = Art.SPRITES[4][11];
	
	//private BufferedImage dead = Game.SPRITES[3][12];
	
	private Animation walkL = new Animation(imageL1, imageL2, imageL1, imageL3);
	private Animation walkR = new Animation(imageR1, imageR2, imageR1, imageR3);
	private Animation walkD = new Animation(imageD1, imageD2, imageD1, imageD3);
	private Animation walkU = new Animation(imageU1, imageU2, imageU1, imageU3);
	
	private boolean direction[] = new boolean[4];

	private Random rand = new Random();

	public Chicken(int x, int y) {
		this.x =x;
		this.y = y;
		image = imageL1;
	}

	public void tick() {
		
		if (rand.nextInt(9000) == 0) {
			PlayState.items.add(new Item(LoadedItems.ITEMLIST.get(20), x, y, false));
		}
		
		if (direction[0]) {
			y-= speed;
			image = imageU1;
		}
		if (direction[1]) { 
			y+= speed;
			image = imageD1;
		}
		if (direction[2]) {
			x-= speed;
			image = imageL1;
		}
		if (direction[3]) {
			x+= speed;
			image = imageR1;
		}

		int randInt = rand.nextInt(100);
		if (randInt == 0) {
			changeDir();
		}

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
		
		walkL.runAnimation();
		walkR.runAnimation();
		walkU.runAnimation();
		walkD.runAnimation();
	}

	public void render(Graphics g) {
		if(!direction[0] && !direction[1] && !direction[2] && !direction[3]) {
			g.drawImage(image, x, y, width, width, null);
		}
		
		if (direction[0]) {
			walkU.drawAnimation(g, x, y, width);
		} else if (direction[1]) { 
			walkD.drawAnimation(g, x, y, width);
		} else if (direction[2]) {
			walkL.drawAnimation(g, x, y, width);
		} else if (direction[3]) {
			walkR.drawAnimation(g, x, y, width);
		}
		
		//g.setColor(Color.YELLOW);
		//g.drawRect(x, y, width, width);
	}

	private void changeDir() {
		if (rand.nextInt(2) == 0) {
			direction[rand.nextInt(4)] = true;
			direction[rand.nextInt(4)] = false;
		} else {
			for (int i = 0; i <direction.length; i++) {
				direction[i] = false;
			}
		}
	}

	public void die() {
		//play death animation
		PlayState.items.add(new Item(LoadedItems.ITEMLIST.get(8),((x + width/2)/32)*32, ((y + width)/32)*32, false));
		PlayState.items.add(new Item(LoadedItems.ITEMLIST.get(13),((x + width/2)/32)*32, ((y + width)/32)*32, false));
		PlayState.items.add(new Item(LoadedItems.ITEMLIST.get(9),((x + width/2)/32)*32, ((y + width)/32)*32, false));
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
