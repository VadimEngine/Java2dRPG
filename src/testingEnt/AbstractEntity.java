package testingEnt;

import java.awt.Graphics;
import java.util.Random;

import other.Animation;

public abstract class AbstractEntity implements Entity {//utitlize the super methods in child classes
	
	protected static Random random = new Random();

	protected boolean solid;
	protected boolean remove;
	//boolean rect
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Animation[] animations;

	public AbstractEntity(int x, int y, Animation...args) {
		this.x = x;
		this.y = y;
		if (args != null) {
			animations = new Animation[args.length];
			for (int i = 0; i < args.length; i++) {
				animations[i] = args[i];
			}
		}
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {

	}
	
	public boolean leftClick(int x, int y) {
		return false;
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isRemove() {
		return remove;
	}
	
}
