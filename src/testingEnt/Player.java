package testingEnt;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import art.Art;
import other.Animation;
import other.Handler;

public class Player extends AbstractEntity {

	private BufferedImage image;
	//private Animation currentAnimanion;
	
	private int imageWidth = 64;
	private int speed = 5;

	private int oldX;
	private int oldY;
	
	private Handler handler;
	
	private ItemType[] inventory = new ItemType[8];
	int numItems;
	

	private List<AbstractEntity> entities;

	/**
	 * animation[0]=down
	 * animation[1]=up
	 * animation[2]=left
	 * animation[3]=right
	 * @param x
	 * @param y
	 */
	public Player(int x, int y, Handler handler, List<AbstractEntity> entities) {
		super(x, y, new Animation(Art.SPRITES[0][3], Art.SPRITES[0][4], Art.SPRITES[0][3],Art.SPRITES[0][5]),
				new Animation(Art.SPRITES[0][12], Art.SPRITES[0][13], Art.SPRITES[0][12],Art.SPRITES[0][14]),
				new Animation(Art.SPRITES[0][6], Art.SPRITES[0][7], Art.SPRITES[0][6],Art.SPRITES[0][8]),
				new Animation(Art.SPRITES[0][9], Art.SPRITES[0][10], Art.SPRITES[0][9],Art.SPRITES[0][11]));
		image = animations[0].getImage(0);
		this.handler = handler;
		width = 20;
		height = 64;
		this.entities = entities;
		oldX = x;
		oldY = y;
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


		//controls
		if (handler.keys[KeyEvent.VK_W]) {
			move(0);
		} 

		if (handler.keys[KeyEvent.VK_S]) {
			move(1);
		} 

		if (handler.keys[KeyEvent.VK_A]) {
			move(2);
		} 

		if (handler.keys[KeyEvent.VK_D]) {
			move(3);
		}
		//controls

		for (int i = 0; i < animations.length; i++) {
			animations[i].runAnimation();
		}
	}

	@Override
	public void render(Graphics g) {//switch case
		if (oldX < x) {//moveright
			animations[3].drawAnimation(g, x, y);
			image = animations[3].getImage(0);
		} else if (oldX > x) {//moveleft
			animations[2].drawAnimation(g, x, y);
			image = animations[2].getImage(0);
		} else if (oldY < y) {//movedown
			animations[0].drawAnimation(g, x, y);
			image = animations[0].getImage(0);
		} else if (oldY > y) {//moveup
			animations[1].drawAnimation(g, x, y);
			image = animations[1].getImage(0);
		} else {
			g.drawImage(image, x, y, imageWidth, imageWidth, null);
		}
	}

	/**
	 * 0 = up, 1 = down, 2 = left, 3 = right 
	 * @param dir
	 */
	private void move(int dir) {//dont pass dir, update it whenever move/tried, change animation if attemp to move
		int newX = x;
		int newY = y;

		switch(dir) {
		case 0:
			newY-= speed;
			break;
		case 1:
			newY+= speed;
			break;
		case 2:
			newX-= speed;
			break;
		case 3:
			newX+= speed;
			break;
		}

		for (int i = 0; i < entities.size(); i++) {
			AbstractEntity e = entities.get(i);
			if (!(e instanceof Player)) {
				if (collide(newX, newY, e) && e.solid) {
					return;
				}
			}
		}
		x = newX;
		y = newY;
	}

	private boolean collide(int x, int y, AbstractEntity e) {
		if ((e.x >= x - e.width && e.x <= x + width) && (e.y >= y - e.height && e.y <= y + height)) {
			return true;
		}

		if ((x >= e.x - width && x <= e.x + e.width) && (y >= e.y - height && y <= e.y + e.height)) {
			return true;
		}
		return false;
	}
	
	public ItemType[] getInventory() {
		return inventory;
	}
	
	public boolean fullinv() {
		return numItems == inventory.length;
	}

	public void removeItem(ItemType item) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getID() == item.getID()) {
				removeItem(i);
				return;
			}
		}
	}
	
	public void addItem(ItemType item) {
		if (!fullinv()) {
			for (int i = 0; i < inventory.length; i++) {
				if (inventory[i] == null) {
					inventory[i] = item;
					numItems++;
					break;
				}
			}
		} else {
			//PlayState.items.add(new Item(item.getType(), ((x + width/2)/32)*32, ((y + width)/32)*32, false));//add item to map
		}
	}

	public void dropItem(int pointer) {
		if (inventory[pointer] != null) {
			entities.add(new TestItem(x + width / 2, y + height, inventory[pointer], entities));
			//PlayState.items.add(new Item(inventory[pointer].getType(), ((x + width/2)/32)*32, ((y + width)/32)*32, true));//add item to map		
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
	
}
