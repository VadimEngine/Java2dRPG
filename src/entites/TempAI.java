package entites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import art.Art;
import other.Animation;
import other.SolidBlock;
import states.PlayState;

public class TempAI {

	private int x;
	private int y;
	private int width = 32;
	private int height = 64;
	private int imageWidth = 64;

	private int job = -1;
	//private int flagX;
	//private int flagY;

	private GameMob player;

	private int cornerX = 0;
	private int cornerY = 0;

	private int speed = 3;
	private int dir = 2;

	private BufferedImage image1 = Art.SPRITES[11][0];//0 3
	private BufferedImage image2 = Art.SPRITES[11][1];//0 4
	private BufferedImage image3 = Art.SPRITES[11][2];//0 5

	private BufferedImage image4 = Art.SPRITES[11][3];//0 6
	private BufferedImage image5 = Art.SPRITES[11][4];//0 7
	private BufferedImage image6 = Art.SPRITES[11][5];//0 8

	private BufferedImage image7 = Art.SPRITES[11][6];//0 9
	private BufferedImage image8 = Art.SPRITES[11][7];//0 10
	private BufferedImage image9 = Art.SPRITES[11][8];//0 11

	private BufferedImage image10 = Art.SPRITES[11][9];//0 12
	private BufferedImage image11 = Art.SPRITES[11][10];//0 13
	private BufferedImage image12 = Art.SPRITES[11][11];//0 14

	private BufferedImage image;//static?

	//private BufferedImage flag = Art.SPRITES[10][7];

	public static BufferedImage[] jobImage = {Art.SPRITES[10][0], Art.SPRITES[10][1], Art.SPRITES[10][2], 
			Art.SPRITES[10][3], Art.SPRITES[10][6], Art.SPRITES[10][7]};

	private Animation walkU = new Animation(image10, image11, image10, image12);
	private Animation walkD = new Animation(image1, image2, image1, image3);
	private Animation walkL = new Animation(image4, image5, image4, image6);
	private Animation walkR = new Animation(image7, image8, image7, image9);

	private TempItem[] inventory = new TempItem[8];
	private int itemCount = 0;

	private boolean moveUp, moveDown, moveLeft, moveRight;

	private boolean rect;

	private boolean tamed;

	public HashMap<Integer, String> jobNames = new HashMap<>();


	public TempAI(int x, int y, GameMob player) {
		this.x = x;
		this.y = y;
		this.player = player;
		image = image1;
		jobNames.put(new Integer(0), "Cook");
		jobNames.put(new Integer(1), "Forager");
		jobNames.put(new Integer(2), "Fisher");
		jobNames.put(new Integer(3), "Hunter");
		jobNames.put(new Integer(4), "Gatherer");
	}

	public void tick() {
		//System.out.println(x + ", " + y);

		if (tamed) {
			if (job == 4) {
				gather();
			} else if (job == 2) {
				fish();
			} else if (job == 0) {
				cook();
			} else if (job == 3) {
				hunt();
			}else {
				moveTo(player.getX(), player.getY());
			}
		}

		walkL.runAnimation();
		walkR.runAnimation();
		walkU.runAnimation();
		walkD.runAnimation();
	}

	public void render(Graphics g) {

		if (!moveUp && !moveDown && !moveLeft && !moveRight) {
			g.drawImage(image, x, y, imageWidth, imageWidth, null);
		}

		if (rect) {
			g.drawRect(x, y, width, height);
			g.setColor(Color.GRAY);
			g.fillRect(x, y - 36, 128, 32);
			g.setColor(Color.WHITE);
			if	(tamed) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.RED);
			}
			g.fillRect(x + 4, y  - 36 + 16, 8, 8);
			g.setColor(Color.WHITE);
			String name = "Plebian";
			if (jobNames.containsKey(job)) {
				name += ": " + jobNames.get(job);
			}
			g.drawString(name, x, y - 24);
			for (int i = 0; i < jobImage.length; i++) {
				g.drawImage(jobImage[i], x + 17 + 17 * i, y - 23, null);
				g.drawRect(x + 17 + 17 *i, y - 23, 16, 16);
			}
			if (job != -1) {
				g.setColor(Color.YELLOW);
				g.drawRect(x + 17 + 17 *job, y - 23, 16, 16);
			}
		}

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

	public void leftClick(int mx, int my) {
		if (tamed) {
			if (my > y - 36 - cornerY && my < y - cornerY && mx > x - cornerX && mx < x + 6 * 16 - cornerX) {
				job = (mx - x + cornerX)/ 16 - 1;
			}
		}
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

	public void setRect() {
		rect = true;
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i]!= null) {
				System.out.println(i + ")" + inventory[i].getName());
			}
		}
	}

	public boolean isInvFull() {
		return itemCount == inventory.length;
	}

	public boolean isInvEmpty() {
		return itemCount == 0;
	}

	public void unRect() {
		rect = false;
	}

	public void tame() {
		tamed = true;
		job = 0;
	}

	public void unTame() {
		tamed = false;
	}

	public boolean isTamed() {
		return tamed;
	}
	//getgui variables

	public boolean isRect() {
		return rect;
	}

	public void setCorners(int x, int y) {
		cornerX = x;
		cornerY = y;
	}

	private void gather() {
		Item closest = null;
		int index = 0;
		for (int i = 0; i < PlayState.items.size(); i++) {
			Item temp1 = PlayState.items.get(i);
			if (!(temp1.getX() == 256 && temp1.getY() == 96) && !temp1.isPicked()) {
				closest = temp1;
				index = i;
				break;
			}
		}
		for (int i = 0; i < PlayState.items.size(); i++) {
			Item temp = PlayState.items.get(i);
			if (!(temp.getX() == 256 && temp.getY() == 96) && closest != null && !temp.isPicked()) {
				if (distance(x, y, temp.getX(), temp.getY()) < distance(x, y, closest.getX(), closest.getY())) {
					closest = temp;
					index = i;
				}
			}
		}
		if (closest != null && !isInvFull()) {
			moveTo(closest.getX(), closest.getY());


			if (colliding(x, y, height, width,
					closest.getX(), closest.getY(), closest.getWidth(), closest.getWidth())) {
				addItem(PlayState.items.get(index).getType());
				PlayState.items.remove(index);
			}
		} else if (!isInvEmpty()){
			dropOff(256, 96);
		} else {
			moveUp = moveDown = moveLeft = moveRight = false;
		}
	}

	private void fish() {

		if (invContain(LoadedItems.ITEMLIST.get(5)) && !isInvFull()) {
			SolidBlock closest = null;

			if (PlayState.blocks.size() >= 1) {
				closest = PlayState.blocks.get(0);
			}

			for (int i = 0; i < PlayState.blocks.size(); i++) {
				SolidBlock temp = PlayState.blocks.get(i);
				if (distance(x, y, temp.getX(), temp.getY()) < distance(x, y, closest.getX(), closest.getY())) {
					closest = temp;
				}
			}
			if (closest != null) {
				moveTo(closest.getX(), closest.getY());
			}

			if (colliding(x, y, height, width, closest.getX(), closest.getY(), closest.getHeight(), closest.getWidth())) {
				if (!isInvFull()) {//add slowly
					addItem(LoadedItems.ITEMLIST.get(10));
				}
			}

		} else {
			if (isInvFull()) {
				dropOff(256, 96);
			} else {
				Item temp = null;
				int index = 0;
				for (int i = 0; i < PlayState.items.size(); i++) {//DOES NOT LOOK FOR CLOSEST
					if (PlayState.items.get(i).getID() == 5) {
						temp = PlayState.items.get(i);
						moveTo(PlayState.items.get(i).getX(), PlayState.items.get(i).getY());
						index = i;
						break;
					}
				}
				if (temp != null && colliding(x, y, height ,width, temp.getX(), temp.getY(), temp.getWidth(), temp.getWidth())) {
					addItem(temp.getType());
					PlayState.items.remove(index);
				}
			}
		}

	}

	private void cook() {
		TempFire fire = null;
		if (PlayState.fires.size() >= 1 && !isInvFull()) {//move to closest fire after gathering raw food
			Item closest = null;
			int index = 0;
			for (int i = 0; i < PlayState.items.size(); i++) {
				Item temp = PlayState.items.get(i);
				if (temp.getID() == 9 || temp.getID() == 10) {
					closest = temp;
					index = i;
					break;
				}
			}

			for (int i = 0; i < PlayState.items.size(); i++) {
				Item temp = PlayState.items.get(i);
				if (temp.getID() == 9 || temp.getID() == 10) {
					if (closest != null && distance(x, y, temp.getX(), temp.getY()) < distance(x, y, closest.getX(), closest.getY())) {
						closest = temp;
						index = i;
					}
				}
			}

			if (closest != null) {
				moveTo(closest.getX(), closest.getY());
				if (colliding(x, y, width, height, closest.getX(), closest.getY(), closest.getWidth(), closest.getWidth())) {
					addItem(PlayState.items.get(index).getType());
					PlayState.items.remove(index);
				}
			} 
		} else {
			if (PlayState.fires.size() >= 0 &&isInvFull()) {
				fire = PlayState.fires.get(0);//find closest fire
				if (invContain(LoadedItems.ITEMLIST.get(9)) || invContain(LoadedItems.ITEMLIST.get(10))) {
					moveTo(fire.getX(),fire.getY());

					if (colliding(x, y, height, width, fire.getX(), fire.getY(), fire.getWidth(), fire.getWidth())) {
						cookItems();
					}
				} else {
					dropOff(256, 96);//drop off when no items are available to pick up
				}
			}
		}
	}

	private void cookItems() {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].getID() == 9) {
				inventory[i] = LoadedItems.ITEMLIST.get(22);
			} else if (inventory[i].getID() == 10) {
				inventory[i] = LoadedItems.ITEMLIST.get(23);
			}
		}
	}

	private void hunt() {
		if (invContain(LoadedItems.ITEMLIST.get(0))) {
			Chicken closest = null;//find closest chicken
			if (PlayState.chickens.size() >= 1) {
				closest = PlayState.chickens.get(0);
				for (int i = 0 ; i < PlayState.chickens.size(); i++) {
					Chicken temp = PlayState.chickens.get(i);
					if (distance(x, y, temp.getX(), temp.getY()) < distance(x, y, closest.getX(), closest.getY())) {
						closest = temp;
					}

				}

				if (closest != null) {
					moveTo(closest.getX(), closest.getY());

					if (distance(x, y, closest.getX(), closest.getY()) <= 32  * 10) {
						PlayState.thrown.add(new ThrownRock(x, y, dir));
						removeItem(0);//remove rock wherever it it
					}
				}

			}
		} else {
			Item closest = null;
			int index = 0;
			for (int i = 0; i < PlayState.items.size(); i++) {
				Item temp = PlayState.items.get(i);
				if (temp.getID() == 0) {
					closest = temp;
					index = i;
					break;
				}
			}
			for (int i = 0; i < PlayState.items.size(); i++) {
				Item temp = PlayState.items.get(i);
				if (temp.getID() == 0 && distance(x, y, temp.getX(), temp.getY()) < distance(x, y, closest.getX(), closest.getY()) ) {
					closest = temp;
					index = i;
				}
			}

			if (closest != null) {
				moveTo(closest.getX(), closest.getY());

				if (colliding(x, y, height, width, closest.getX(), closest.getY(), closest.getWidth(), closest.getWidth())) {
					addItem(closest.getType());
					PlayState.items.remove(index);
				}
			}



		}
	}

	private int distance(int x1, int y1, int x2, int y2) {
		return (int)Math.sqrt(Math.pow((x1 - x2),2) + Math.pow((y1 - y2),2));
	}

	private void moveTo(int tx, int ty) {

		if (tx < x - width) {
			x-= speed;
			moveRight = false;
			moveUp = false;
			moveDown = false;
			moveLeft = true;
			dir = 0;
		} else if (tx > x + width) {
			x += speed;
			moveRight = true;
			moveLeft = false;
			moveUp = false;
			moveDown = false;
			dir = 1;
		}

		if (ty < y) {
			y-= speed;
			moveUp = true;
			moveDown = false;
			moveLeft = false;
			moveRight = false;
			dir = 3;
		} else if (ty > y + width) {
			y+= speed;
			moveDown = true;
			moveUp = false;
			moveLeft = false;
			moveRight = false;
			dir = 2;
		}

	}

	private void dropOff(int dx, int dy) {
		moveTo(dx, dy);
		if(colliding(x, y, height, width, dx, dy, 32, 32)) {
			for (int i = 0; i < inventory.length; i++) {
				invDrop(i);
			}
		}
	}

	private void invDrop(int pointer) {
		if (inventory[pointer] != null) {
			PlayState.items.add(new Item(inventory[pointer], 256, 96, true));//ONLY DROP IN ZONE
			removeItem(pointer);
		}
	}

	private boolean invContain(TempItem item) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getID() == item.getID()) {
				return true;
			}
		}
		return false;
	}

	private boolean colliding(int x1, int y1, int height1, int width1,
			int x2, int y2, int height2, int width2) {
		if ((x2 >= x1 - width2 && x2 <= x1 + width1) && (y2 >= y1 - height2 && y2 <= y1 + height1)) {
			return true;
		}

		if ((x1 >= x2 - width1 && x1 <= x2 + width2) && (y1 >= y2 - height1 && y1 <= y2 + height2)) {
			return true;
		}

		return false;
	}

	public void addItem(TempItem item) {
		if (itemCount != inventory.length) {
			for (int i = 0; i < inventory.length; i++) {
				if (inventory[i] == null) {
					inventory[i] = item;
					itemCount++;
					break;
				}
			}
		}
	}

	public void removeItem(int pointer) {
		if (inventory[pointer] != null) {
			inventory[pointer] = null;
			itemCount--;
		}
	}

	public String getJob() {
		if (jobNames.containsKey(job)) {
			return jobNames.get(job);
		} else {
			return "Unemployed";
		}
	}

}
