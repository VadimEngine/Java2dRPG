package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TempButton {
	
	private String text;
	private int size;
	
	private boolean highlight;
	
	//boolean centered??
	
	
	public TempButton(String text, int size) {//size is string size
		this.text = text;
		this.size = size;
	}
	
	public TempButton(String text) {
		this(text, 32);
	}
	
	public void render(Graphics g, int x, int y) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, size * 8, size + 8);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Console", 1, size));
		g.drawString(text, x + (size * 8)/2 - text.length() * (size) / 4, y + size);
		
		if (highlight) {
			g.setColor(Color.WHITE);
			g.drawRect(x, y, size * 8, size + 8);
		} else {
			g.setColor(Color.BLACK);
			g.drawRect(x, y, size * 8, size + 8);
		}
		
//		g.setColor(Color.GRAY);
//		g.fillRect(x, y, 250, 38);
//		g.setColor(Color.WHITE);
//		g.setFont(new Font("Console", 1, 32));
//		g.drawString(text, x  + 117 - text.length() * 8, y+28);
//		
//		
//		
//		if (highlight) {
//			g.setColor(Color.WHITE);
//			g.drawRect(x, y, 250, 38);
//		}
	}
	
//	public boolean intersects() {
//		
//	}
	
	public String getText() {
		return text;
	}
	
	public void highlight() {
		highlight = true;
	}
	
	public void unhighlight() {
		highlight = false;
	}

}
