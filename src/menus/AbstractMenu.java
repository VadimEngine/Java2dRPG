package menus;

import java.awt.Color;
import java.awt.Graphics;

public abstract class AbstractMenu implements Menu {//bool require focus??

	protected int width;
	protected int height;
	protected int bufferSpace;
	protected boolean visible;

	public AbstractMenu(int width, int height, int bufferSpace, boolean visible) {
		this.width = width;
		this.height = height;
		this.bufferSpace = bufferSpace;
		this.visible = visible;
	}

	@Override
	public void tick() {};

	@Override
	public void render(Graphics g, int cornerX, int cornerY) {
		g.setColor(Color.GRAY);
		g.fillRect(cornerX, cornerY, width, height);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getBufferSpace() {
		return bufferSpace;
	}
	
	@Override
	public void setWidth(int width) {
		this.width = width;
	}
	
	@Override
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	

}
