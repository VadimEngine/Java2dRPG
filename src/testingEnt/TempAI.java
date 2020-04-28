package testingEnt;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

import art.Art;
import other.Animation;
import other.Handler;

public class TempAI extends AbstractEntity {
	
	private BufferedImage image;
	
	private List<AbstractEntity> entities;
	
	private int speed = 4;
	private int imageWidth = 64;
	
	private int oldX;
	private int oldY;
	
	private HashMap<Integer, String> jobNames = new HashMap<>();
	
	private boolean tamed;

	/**
	 * up[0]
	 * down[1]
	 * left[2]
	 * right[3]
	 * @param x
	 * @param y
	 * @param handler
	 * @param entities
	 */
	public TempAI(int x, int y, List<AbstractEntity> entities) {
		super(x, y, new Animation(Art.SPRITES[11][9], Art.SPRITES[11][10], Art.SPRITES[0][9],Art.SPRITES[11][11]),
				new Animation(Art.SPRITES[11][0], Art.SPRITES[11][1], Art.SPRITES[11][0],Art.SPRITES[11][2]),
				new Animation(Art.SPRITES[11][3], Art.SPRITES[11][4], Art.SPRITES[11][3],Art.SPRITES[11][5]),
				new Animation(Art.SPRITES[11][6], Art.SPRITES[11][7], Art.SPRITES[11][6],Art.SPRITES[11][8]));
		image = animations[0].getImage(0);
		width = 32;
		height = 64;
		this.entities = entities;
		oldX = x;
		oldY = y;
	}
	
	@Override
	public void tick() {
		
	}
	
	@Override
	public void render(Graphics g) {
		
	}
	
	public void AI() {
		
	}
}
