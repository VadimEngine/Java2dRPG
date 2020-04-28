package entites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public interface GameItem {

	void render(Graphics g);
	
	void tick();
	
	String getName();
	
	String getComment();
	
	int getX();
	
	int getY();
	
	int getID();
	
	int getWidth();
	
	TempItem getType();
	
	BufferedImage getImage();
	
	void setX(int x);
	
	void setY(int y);
	
	void setType(TempItem type);
	
	void setRect();
	
	void unRect();
	
	
	//boolean isRemoved
	
}
