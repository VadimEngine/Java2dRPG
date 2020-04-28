package entites;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import art.Art;
import other.Animation;
import other.Handler;
import states.PlayState;

public class GameMob {

	private int x, y;
	private int speed = 5;
	private int width = 32;
	private int height = 64;
	private int imageWidth = 64;

	private int dir = 2;

	private int invPointer = 0;
	

	private BufferedImage image1 = Art.SPRITES[0][3];//0 3
	private BufferedImage image2 = Art.SPRITES[0][4];//0 4
	private BufferedImage image3 = Art.SPRITES[0][5];//0 5

	private BufferedImage image4 = Art.SPRITES[0][6];//0 6
	private BufferedImage image5 = Art.SPRITES[0][7];//0 7
	private BufferedImage image6 = Art.SPRITES[0][8];//0 8

	private BufferedImage image7 = Art.SPRITES[0][9];//0 9
	private BufferedImage image8 = Art.SPRITES[0][10];//0 10
	private BufferedImage image9 = Art.SPRITES[0][11];//0 11

	private BufferedImage image10 = Art.SPRITES[0][12];//0 12
	private BufferedImage image11 = Art.SPRITES[0][13];//0 13
	private BufferedImage image12 = Art.SPRITES[0][14];//0 14


	private BufferedImage image;//static?

	private Animation walkU = new Animation(image10, image11, image10, image12);
	private Animation walkD = new Animation(image1, image2, image1, image3);
	private Animation walkL = new Animation(image4, image5, image4, image6);
	private Animation walkR = new Animation(image7, image8, image7, image9);

	private boolean picking;

	private int health;

	int numItems = 0;
	private Item[] inventory = new Item[8];

	private boolean moveUp, moveDown, moveLeft, moveRight;

	private boolean moveUp2, moveDown2, moveLeft2, moveRight2;


	public GameMob() {
		x = 320;
		y = 240;
		health = 100;
		image = image1;
		moveUp2 = moveDown2 = moveLeft2 = moveRight2 = true;
	}

	public void render(Graphics g) {
		
		if (!moveUp && !moveDown && !moveLeft && !moveRight) {
			g.drawImage(image, x, y, imageWidth, imageWidth, null);
		}

		//g.drawRect(x, y, width, height);

		if (moveLeft) {
			walkL.drawAnimation(g, x, y);
		}
		else if (moveRight) {
			walkR.drawAnimation(g, x, y);
		}
		else if (moveUp) {
			walkU.drawAnimation(g, x, y);
		}
		else if (moveDown) {
			walkD.drawAnimation(g, x, y);
		}

	}

	public void tick(Handler handler) {
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

		if (handler.keys[KeyEvent.VK_W]) {
			moveUp = true;
			image = image10;
			//y -= speed;
			dir = 3;
		} else {
			moveUp = false;
		}

		if (handler.keys[KeyEvent.VK_S]) {
			moveDown = true;
			image = image1;
			//y += speed;
			dir = 2;
		} else {
			moveDown = false;
		}

		if (handler.keys[KeyEvent.VK_A]) {
			moveLeft = true;
			image = image4;
			//x -= speed;
			dir = 0;
		} else {
			moveLeft = false;
		}

		if (handler.keys[KeyEvent.VK_D]) {
			moveRight = true;
			image = image7;
			//x+= speed;		
			dir = 1;
		} else {
			moveRight = false;
		}

		if (handler.keys[KeyEvent.VK_Z]) {
			handler.keys[KeyEvent.VK_Z] = false;
			picking = true;
		} else {
			picking = false;
		}

		if (handler.keys[KeyEvent.VK_P]) {
			health--;
		}

		if (moveLeft && moveLeft2) {
			x -= speed;
		}

		if (moveRight && moveRight2) {
			x+= speed;
		}

		if (moveUp && moveUp2) {
			y-= speed;
		}

		if (moveDown && moveDown2) {
			y+= speed;
		}

		walkL.runAnimation();
		walkR.runAnimation();
		walkU.runAnimation();
		walkD.runAnimation();
		moveUp2 = moveDown2 = moveLeft2 = moveRight2 = true;
	}

	public int getHealth() {
		return health;
	}
	public void setHealth(int newHealth) {
		health = newHealth;
	}

	public Item[] getInventory() {
		return inventory;
	}

	public boolean fullinv() {
		return numItems == inventory.length;
	}

	public void removeItem(TempItem item) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getID() == item.getID()) {
				removeItem(i);
				return;
			}
		}
	}


	public void addItem(Item item) {
		if (!fullinv()) {
			for (int i = 0; i < inventory.length; i++) {
				if (inventory[i] == null) {
					inventory[i] = item;
					numItems++;
					break;
				}
			}
		} else {
			PlayState.items.add(new Item(item.getType(), ((x + width/2)/32)*32, ((y + width)/32)*32, false));
		}
	}

	public void dropItem(int pointer) {
		if (inventory[pointer] != null) {
			PlayState.items.add(new Item(inventory[pointer].getType(), ((x + width/2)/32)*32, ((y + width)/32)*32, true));			
			inventory[pointer] = null;
			numItems--;
		}
	}

	public void removeItem(int i) {
		if (inventory[i] != null) {
			inventory[i] = null;
			numItems--;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public boolean isPicking() {
		return picking;
	}

	public int getDir() {
		return dir;
	}

	public int getPointer() {
		return invPointer;
	}

	public void setPointer(int pointer) {
		invPointer = pointer;
	}

	public void blockLeft() {
		moveLeft2 = false;
	}

	public void blockRight() {
		moveRight2 = false;
	}

	public void blockUp() {
		moveUp2 = false;
	}

	public void blockDown() {
		moveDown2 = false;
	}

}
