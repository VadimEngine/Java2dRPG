package entites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class AbstractItem implements GameItem {

	protected TempItem type;
	protected int x, y, width;
	protected boolean rect;

	public AbstractItem(TempItem type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		width = 32;
	}

	public void render(Graphics g) {
		g.drawImage(type.getImage(), x, y, width, width, null);
		if (rect) {
			g.setColor(Color.YELLOW);
			g.drawRect(x, y, width, width);
		}
	}

	public void tick() {

	}
	
	public String getName() {
		return type.getName();
	}
	
	public String getComment() {
		return type.getCommet();
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
	
	public TempItem getType() {
		return type;
	}

	public int getID() {
		return type.getID();
	}

	public BufferedImage getImage() {
		return type.getImage();
	}
	
	public void setX(int x) {
		this.x = x;	
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setType(TempItem type) {
		this.type = type;
	}
	
	public void setRect() {
		rect = true;
	}
	
	public void unRect() {
		rect = false;
	}

}
