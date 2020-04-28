package testingEnt;

import java.awt.Graphics;

import art.Art;
import other.Animation;

public class Bush extends AbstractEntity {
	
	private static final int BERRY_TIME = 1000;
	private static final int GROW_TIME = 1000;
	
	private boolean picked;
	private boolean chopped;
	
	private int timer1;
	private int timer2;
	


	public Bush(int x, int y) {
		super(x, y, new Animation(Art.SPRITES[5][0], Art.SPRITES[5][1], Art.SPRITES[5][2]));
	}
	
	@Override
	public void tick() {
		
		if (!picked && !chopped) {
			timer1 = 0;
			timer2 = 0;
		}
		
		if (picked && !chopped) {
			timer1++;
		}
		
		if (chopped) {
			timer2++;
		}
		
		if (timer1 >= BERRY_TIME) {
			picked = false;
		}
		
		if (timer2 >= GROW_TIME) {
			chopped = false;
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		
		if (!picked && !chopped) {
			g.drawImage(animations[0].getImage(0), x, y, null);
		}
		
		if (picked && !chopped) {
			g.drawImage(animations[0].getImage(1), x, y, null);
		}
		
		if (chopped) {
			g.drawImage(animations[0].getImage(2), x, y, null);
		}
		
	}
	
	public void pick() {
		
	}
	
	public void chop() {
		
	}
}
