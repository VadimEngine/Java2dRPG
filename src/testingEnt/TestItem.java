package testingEnt;

import java.awt.Graphics;
import java.util.List;

import other.Animation;

public class TestItem extends AbstractEntity {
	
	private ItemType type;
	
	private List<AbstractEntity> entities;
	

	public TestItem(int x, int y, ItemType type, List<AbstractEntity> entities) {
		super(x, y, new Animation(type.getImage()));
		this.type = type;
		this.entities = entities;
		this.width = 32;
		this.height = 32;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(type.getImage(), x, y, 32, 32, null);
	}
	
	@Override
	public boolean leftClick(int x, int y) {//check collisonds in add item method??
		
		if (collide(this.x, this.y, entities.get(0))) {
			((Player)entities.get(0)).addItem(type);
			System.out.println("ADD " + type.getName());
			remove = true;
			return true;
		}
		
		return false;
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
	
}
