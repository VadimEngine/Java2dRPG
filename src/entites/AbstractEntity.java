package entites;

import java.awt.Graphics;

public class AbstractEntity {
	
	protected int x;
	protected int y;
	
	
	public AbstractEntity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		
	}
	
	public void tick() {
		
	}

}
