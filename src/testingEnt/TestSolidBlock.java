package testingEnt;

import java.awt.Graphics;

import other.Animation;

public class TestSolidBlock extends AbstractEntity{

	public TestSolidBlock(int x, int y) {
		this(x,y,32,32);
	}
	
	public TestSolidBlock(int x, int y, int width, int height) {
		super(x, y, (Animation)null);
		super.width = width;
		super.height = height;
		solid = true;
	}
	
	@Override
	public void render(Graphics g) {
		
	}
}
