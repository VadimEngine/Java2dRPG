package menus;

import java.awt.Graphics;

public interface Menu {
	
	void tick();
	
	void render(Graphics g, int cornerX, int cornerY);
	
	int getWidth();
	
	int getHeight();
	
	int getBufferSpace();
	
	void setWidth(int width);
	
	void setHeight(int height);
	
	boolean isVisible();
	
	void setVisible(boolean visible);

}
