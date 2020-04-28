package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import art.Art;
import menus.Menu;
import menus.StatusMenu2;
import menus.TestMenu;
import other.Game;
import other.Handler;
import other.MiniMap;
import testingEnt.AbstractEntity;
import testingEnt.ItemType;
import testingEnt.Player;
import testingEnt.TestItem;
import testingEnt.TestSolidBlock;

public class PlayState2 extends AbstractGameState {

	private static Random random = new Random();

	private MiniMap miniMap;
	//player pointer
	//pointer to mouse item, action on mouse, update on mouse move, if mousepointer==null then nothing

	private List<AbstractEntity> entities = new ArrayList<>();//sort by y cords, use a iterator

	private int cornerX;//utilize these, record/comment if using in methods
	private int cornerY;

	//private selected Action

	//private StatusMenu2 sm;

	//array with index associated with location
	private Menu bottom;
	private Menu CenterMenu;
	private Menu LeftMenu;

	PlayState2(Handler handler) {
		super(handler);

		entities.add(new Player(50,50,handler, entities));
		setCorners((Player)entities.get(0));
		entities.add(new TestSolidBlock(250,250, 48, 48));
		entities.add(new testingEnt.Chicken(90,90, entities));
		entities.add(new TestItem(70, 70, ItemType.ITEMLIST.get(0), entities));

		//sm = new StatusMenu2((Player)entities.get(0));
		bottom = new StatusMenu2((Player)entities.get(0));
		CenterMenu = new TestMenu(250, 250, 0, false);
		LeftMenu = new TestMenu(100, 100, 4, false);


		miniMap = new MiniMap(Art.MAP, (Player)entities.get(0));
		for (int i = 0; i < Art.MAP.length; i++) {
			for (int j = 0; j <Art.MAP[0].length; j++) {
				if (Art.MAP[i][j].equals(new Color(0,0,255))) {
					entities.add(new TestSolidBlock(32 * i, 32 * j));
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(-cornerX, -cornerY);

		for (int i = 0; i < Art.MAP.length; i++) {//map instead of if else
			for (int j = 0; j < Art.MAP[0].length; j++) {
				//if (i > entities.get(0).getX() - 20 && i < entities.get(0).getX() + 20) {
					g.drawImage(Art.COLORMAP.get(Art.MAP[i][j]), 32*i, 32*j, 32, 32, null);
				//}
			}
		}

		for (int i = entities.size() - 1; i >= 0; i--) {
			entities.get(i).render(g);
		}

		g.drawString("FPS: " + Game.FPS, 16 + cornerX, 16 + cornerY);
		miniMap.render(g, cornerX, cornerY);
		//sm.render(g, cornerX, cornerY);

		//renders the bottom menu at the bottom-middle of the screen, corners included
		if (bottom.isVisible()) {
			bottom.render(g, cornerX + (Game.width / 2) - (bottom.getWidth() / 2),
					cornerY + Game.height - bottom.getHeight() - bottom.getBufferSpace());
		}
		//renders in the middle of the screen, corners included
		if (CenterMenu.isVisible()) {
			CenterMenu.render(g, cornerX + Game.width / 2 - CenterMenu.getWidth() / 2, cornerY + Game.height / 2 - CenterMenu.getHeight() / 2);
		}
		//renders in the left-middle of the screen
		if (LeftMenu.isVisible()) {
			LeftMenu.render(g, cornerX + LeftMenu.getBufferSpace(), cornerY + Game.height / 2 - LeftMenu.getHeight());
		}
	}

	@Override
	public void tick() {
		//sm.tick();
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isRemove()) {
				entities.remove(i);
				continue;
			}
			entities.get(i).tick();
		}
		setCorners((Player)entities.get(0));
		miniMap.tick2();

		if (random.nextInt(1000) == 0) {//other items
			int rand = random.nextInt(24);
			entities.add(new TestItem((random.nextInt(64)) * 32, (random.nextInt(64)) * 32, ItemType.ITEMLIST.get(rand), entities));
		}

		if (bottom.isVisible()) {
			bottom.tick();
		}

		if (CenterMenu.isVisible()) {
			CenterMenu.tick();
		}

		if (LeftMenu.isVisible()) {
			LeftMenu.tick();
		}
	}

	public void addItem(AbstractEntity entity) {
		entities.add(entity);
	}

	@Override
	public void leftClick(int x, int y) {

		//sm.leftClick(x, y);

		for (int i = 0; i < entities.size(); i++ ) {
			if (mouseIntersect(x,y, entities.get(i)) && entities.get(i).leftClick(x, y)) {
				break;
			}
		}

	}

	@Override
	public void rightClick(int x, int y) {

	}

	@Override
	public void moveWheel(int rot) {


	}

	@Override
	public void mouseMove(int x, int y) {
		//if menu!=null if inside sidemenu else select entity

	}

	private void setCorners(Player player) {
		cornerX = player.getX() - 320;
		cornerY= player.getY() - 240;

		if (player.getX() < 320) {
			cornerX = 0;
		}

		if (player.getX() > 31 * 64 - 320 + 32) {
			cornerX = 31 * 64 - 640 + 32;
		}

		if (player.getY() < 240) {
			cornerY = 0;
		}

		if (player.getY() > 31 * 64 - 240 + 32) {
			cornerY = 31 * 63 - 480 + 64;
		}
	}

	private boolean mouseIntersect(int ex, int ey, int x, int y, int width, int height) {
		int mx = ex + cornerX;
		int my = ey + cornerY;

		if ((mx > x && mx < x + width) && (my > y && my < y + height)) {
			return true;
		}

		return false;
	}

	private boolean mouseIntersect(int ex, int ey, AbstractEntity e) {
		return mouseIntersect(ex, ey, e.getX(), e.getY(), e.getWidth(), e.getHeight());
	}


}
