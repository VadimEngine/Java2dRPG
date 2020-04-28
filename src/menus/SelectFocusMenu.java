package menus;

import java.awt.Color;
import java.awt.Graphics;

import entites.TempAI;

public class SelectFocusMenu {

	public static TempAI FocusAi; 

	private TempAI ai;
	private int x;
	private int y;

	public SelectFocusMenu(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 150, 100);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 150, 100);
		g.setColor(Color.WHITE);
		if (ai != null) {
			g.drawString(ai.getJob(), x, y + 16);
			
			
			

			
			g.setColor(Color.GRAY);
			
			g.setColor(Color.WHITE);
			if	(ai.isTamed()) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.RED);
			}
			g.fillRect(x + 4, y + 16 + 10, 8, 8);
			g.setColor(Color.WHITE);
			
			for (int i = 0; i < TempAI.jobImage.length; i++) {
				g.drawImage(TempAI.jobImage[i], x + 17 + 17 * i, y + 23, null);
				g.drawRect(x + 17 + 17 *i, y + 23, 16, 16);
			}
//			if (job != -1) {
//				g.setColor(Color.YELLOW);
//				g.drawRect(x + 17 + 17 *job, y - 23, 16, 16);
//			}
		
			
			
		}

	}

	public void tick() {

	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setAI(TempAI ai) {
		this.ai = ai;
	}


}
