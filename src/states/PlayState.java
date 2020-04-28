package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import art.Art;
import entites.Bush;
import entites.Chicken;
import entites.GameMob;
import entites.Item;
import entites.LoadedItems;
import entites.TempAI;
import entites.TempFire;
import entites.ThrownRock;
import menus.StatusMenu;
import menus.TempButton;
import other.Game;
import other.Handler;
import other.MiniMap;
import other.SolidBlock;
import testingEnt.AbstractEntity;
import testingEnt.Player;
import testingEnt.TestSolidBlock;

/**
 * BIG MESS OF A CLASS NEEDS TO BE CLEANED
 * @author Vadim Goncharuk
 *
 */
public class PlayState extends AbstractGameState {
	
	private static Random random = new Random();

	private GameMob temp = new GameMob();
	private StatusMenu sm = new StatusMenu(temp);

	private MiniMap miniMap;

	public static ArrayList<Item> items = new ArrayList<Item>();//FIX THIS
	public static ArrayList<Chicken> chickens = new ArrayList<>();//FIX THIS
	public static ArrayList<Bush> bushes = new ArrayList<>();//FIX THIS
	public static ArrayList<ThrownRock> thrown = new ArrayList<>();//FIX THIS
	public static ArrayList<TempFire> fires = new ArrayList<>();//FIX THIS
	public static ArrayList<SolidBlock> blocks = new ArrayList<>();//FIX THIS

	public static ArrayList<TempAI> AIS = new ArrayList<>();

	private int cornerY = 0;
	private int cornerX = 0;

	private boolean paused;

	private TempButton mainMenu = new TempButton("Main Menu");
	private TempButton settings = new TempButton("Settings");
	private TempButton cancel = new TempButton("Cancel");//save game, exit game
	
	//private ArrayList<TempButton> buttons = new ArrayList<>(); 

	private List<AbstractEntity> entities = new ArrayList<>();
	
	public PlayState(Handler handler) {
		super(handler);
		
		entities.add(new Player(50,50,handler, entities));
		entities.add(new TestSolidBlock(250,250, 48, 48));
		entities.add(new testingEnt.Chicken(90,90, entities));
		//testing purposes
		items.add(new Item(LoadedItems.ITEMLIST.get(0), 256, 96, false));
		items.add(new Item(LoadedItems.ITEMLIST.get(5), 256, 96, false));
		items.add(new Item(LoadedItems.ITEMLIST.get(1), 256, 96, false));
		items.add(new Item(LoadedItems.ITEMLIST.get(22), 256, 96, false));
		bushes.add(new Bush(250, 250));
		
		AIS.add(new TempAI(50,100, temp));
		AIS.add(new TempAI(50,100 + 64, temp));
		AIS.add(new TempAI(50,100 + 64, temp));
		AIS.add(new TempAI(500,700, temp));
		//chickens.add(new Chicken(100,100));
		//testing purposes
		
		miniMap = new MiniMap(Art.MAP, temp);
		

		for (int i = 0; i < Art.MAP.length; i++) {
			for (int j = 0; j <Art.MAP[0].length; j++) {
				if (Art.MAP[i][j].equals(new Color(0,0,255))) {
					blocks.add(new SolidBlock(32 * i, 32 * j));
				}
			}
		}

	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		int tranX = -temp.getX() + 320;
		int tranY = -temp.getY() + 240;
		if (temp.getX() < 320 ) {
			tranX = 0;
		} 
		if (temp.getX() > 31 * 64 - 320 + 32) {
			tranX = -(31 * 64) + 640 - 32;
		}

		if (temp.getY() < 240) {
			tranY = 0;
		}

		if (temp.getY() < 240) {
			tranY = 0;
		} 

		if (temp.getY() > 31 * 64 - 240 + 32) {
			tranY = -(31 * 64) + 480 - 32;
		}

		g2d.translate(tranX, tranY);


		
		
		for (int i = 0; i < Art.MAP.length; i++) {
			for (int j = 0; j < Art.MAP[0].length; j++) {

				if (Art.MAP[i][j].equals(new Color(0,255,0))) {
					g.drawImage(Art.SPRITES[1][0], 32*i, 32*j, 32, 32, null);//grass
				} else if (Art.MAP[i][j].equals(new Color(0,155,0))) {
					g.drawImage(Art.SPRITES[1][1], 32*i, 32*j, 32, 32, null);//trees
				} else if (Art.MAP[i][j].equals(new Color(0,0,255))) {
					g.drawImage(Art.SPRITES[1][2], 32*i, 32*j, 32, 32, null);//water
				} else if (Art.MAP[i][j].equals(new Color(255,255,0))) {
					g.drawImage(Art.SPRITES[1][3], 32*i, 32*j, 32, 32, null);//sand
				} else if (Art.MAP[i][j].equals(new Color(255,255,255))) {
					g.drawImage(Art.SPRITES[1][4], 32*i, 32*j, 32, 32, null);//snow
				} else if (Art.MAP[i][j].equals(new Color(155,155,155))) {
					g.drawImage(Art.SPRITES[1][5], 32*i, 32*j, 32, 32, null);//stone
				} else if (Art.MAP[i][j].equals(new Color(135,90,30))) {
					g.drawImage(Art.SPRITES[1][6], 32*i, 32*j, 32, 32, null);//dirt
				} else if (Art.MAP[i][j].equals(new Color(180,100,70))) {
					g.drawImage(Art.SPRITES[1][15], 32*i, 32*j, 32, 32, null);//tilledDirt
				}
			}
		}
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}

		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(g);
		}
		for (int i = 0; i < chickens.size(); i++) {
			chickens.get(i).render(g);
		}

		for (int i = 0; i < bushes.size(); i++) {
			bushes.get(i).render(g);
		}
		for (int i = 0; i < fires.size(); i++) {
			fires.get(i).render(g);
		}
		for (int i = 0; i < thrown.size(); i++) {
			thrown.get(i).render(g);
		}

		for (int i = 0; i < AIS.size(); i++) {
			AIS.get(i).render(g);
		}
		temp.render(g);
		sm.render(g);
		miniMap.render(g);
		g.setColor(Color.YELLOW);
		g.drawString("FPS: " + Game.FPS, 16 + cornerX, 16 + cornerY);

		if (paused) {
			drawMenu(g);
		}
	}

	public void drawMenu(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);

		g.fillRect(cornerX + 210, cornerY + 50, 260, 300);
		g.setColor(Color.BLACK);
		g.drawRect(cornerX + 210, cornerY + 50, 260, 300);

		g.drawString("Menu", cornerX + 315, cornerY + 70);



		mainMenu.render(g, cornerX + 215, cornerY + 100);
		settings.render(g, cornerX + 215, cornerY + 150);
		cancel.render(g, cornerX + 215, cornerY + 200);
	}

	public void tick() {
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}

		if (!paused) {
			temp.tick(handler);
		}
		sm.tick(handler);
		for (int i = 0; i < blocks.size(); i++) {
			SolidBlock block = blocks.get(i);
			if (colliding(temp.getX(), temp.getY(), temp.getHeight(), temp.getWidth(), block.getX(), block.getY(), block.getHeight(), block.getWidth())) {
				if (temp.getX() < block.getX() && temp.getY() > block.getY() - temp.getHeight() + 2 && temp.getY() < block.getY() + block.getWidth() - 2) {
					temp.blockRight();//Draw 4 rectangles around edges, block players depending on with rectangle
				}
				else if (temp.getX() > block.getX() && temp.getY() > block.getY() - temp.getHeight() + 2 && temp.getY() < block.getY() + block.getWidth() - 2) {
					temp.blockLeft();
				}

				else if (temp.getY() > block.getY() && temp.getX() > block.getX() - temp.getWidth() + 2 && temp.getX() < block.getX() + block.getWidth() - 2) {
					temp.blockUp();
				}

				else if (temp.getY() < block.getY() && temp.getX() > block.getX() - temp.getWidth() + 2 && temp.getX() < block.getX() + block.getWidth() - 2) {
					temp.blockDown();
				}
				if (!paused) {
					if (handler.keys[KeyEvent.VK_F] && temp.getInventory()[temp.getPointer()] != null && temp.getInventory()[temp.getPointer()].getID() == 5) {
						handler.keys[KeyEvent.VK_F] = false;//REMOVE THIS
						temp.addItem(new Item(LoadedItems.ITEMLIST.get(10), 0, 0, false));
						sm.gainXP(2, 30);//gain fishing xp
					}
				}
			}
		}

		for (int i = 0; i < bushes.size(); i++) {
			Bush bush = bushes.get(i);
			bush.tick();


			if (colliding(temp.getX(), temp.getY(), temp.getHeight(), temp.getWidth(), bush.getX(), bush.getY(), bush.getHeight(), bush.getWidth())) {

				if (temp.getX() < bush.getX() && temp.getY() > bush.getY() - temp.getHeight() + 2 && temp.getY() < bush.getY() + bush.getWidth() - 2) {
					temp.blockRight();//Draw 4 rectangles around edges, block players depending on with rectangle
				}
				if (temp.getX() > bush.getX() && temp.getY() > bush.getY() - temp.getHeight() + 2 && temp.getY() < bush.getY() + bush.getWidth() - 2) {
					temp.blockLeft();
				}

				if (temp.getY() > bush.getY() && temp.getX() > bush.getX() - temp.getWidth() + 2 && temp.getX() < bush.getX() + bush.getWidth() - 2) {
					temp.blockUp();
				}

				if (temp.getY() < bush.getY() && temp.getX() > bush.getX() - temp.getWidth() + 2 && temp.getX() < bush.getX() + bush.getWidth() - 2) {
					temp.blockDown();
				}


				if (temp.isPicking() && bush.hasBerries() && (temp.getInventory()[temp.getPointer()] == null || temp.getInventory()[temp.getPointer()].getID() != 2)) {
					temp.addItem(new Item(LoadedItems.ITEMLIST.get(3),0,0, true));//add item types instead
					bush.pick();
					sm.gainXP(1, 30);//gain gathering xp//gain foraging xp
				} else if (temp.isPicking() && bush.isGrown() && temp.getInventory()[temp.getPointer()] != null && temp.getInventory()[temp.getPointer()].getID() == 2) {
					if (bush.hasBerries()) {
						temp.addItem(new Item(LoadedItems.ITEMLIST.get(3),0,0, true));//add item types instead
						sm.gainXP(1, 30);//gain foraging xp
					}
					temp.addItem(new Item(LoadedItems.ITEMLIST.get(1),0,0, true));//add item types instead
					bush.chop();
					sm.gainXP(1, 30);//gain gathering xp//foraging xp
				}
			}
		}

		for (int i = 0; i < fires.size(); i++) {
			fires.get(i).tick();
		}

		if (random.nextInt(1000) == 0) {//other items
			//			int rand = random.nextInt(15);
			//			items.add(new Item(Game.items.get(rand), (random.nextInt(64)) * 32, (random.nextInt(64)) * 32));
			int rand = random.nextInt(2);
			items.add(new Item(LoadedItems.ITEMLIST.get(rand), (random.nextInt(64)) * 32, (random.nextInt(64)) * 32, false));
		}

		if (random.nextInt(500) == 0) {
			chickens.add(new Chicken((random.nextInt(64)) * 32, (random.nextInt(64)) * 32));
		}

		for (int i = 0; i < items.size(); i++) {
			items.get(i).tick();
			if (colliding(temp.getX(), temp.getY(), temp.getHeight(), temp.getWidth(),
					items.get(i).getX(), items.get(i).getY(), items.get(i).getWidth(), items.get(i).getWidth())
					&& temp.isPicking() && !temp.fullinv()) {
				temp.addItem(items.get(i));
				items.remove(i);
				sm.gainXP(6, 30);//gain gathering xp
				break;

			}
		}

		for (int i = 0; i < AIS.size(); i++) {
			AIS.get(i).tick();
			AIS.get(i).setCorners(cornerX, cornerY);
		}

		if (handler.keys[KeyEvent.VK_ESCAPE]) {
			handler.keys[KeyEvent.VK_ESCAPE] = false;
			//handler.setGameState(new MenuState(handler));
			paused = !paused;
		}

		miniMap.tick();
		for (int i = 0; i < chickens.size(); i++) {
			chickens.get(i).tick();
			//			if (colliding(temp.getX(), temp.getY(), temp.getWidth(),
			//					chickens.get(i).getX(), chickens.get(i).getY(), chickens.get(i).getWidth())) {
			//				chickens.get(i).die();
			//				chickens.remove(i);
			//			}
		}

		for (int i = 0; i < thrown.size(); i++) {
			thrown.get(i).tick();
			if (thrown.get(i).isDropped()) {
				items.add(new Item(LoadedItems.ITEMLIST.get(0), (thrown.get(i).getX() / 32) * 32, (thrown.get(i).getY() / 32) * 32, true));
				thrown.remove(i);
			}
		}

		for (int i = 0; i < thrown.size(); i++) {
			ThrownRock temp = thrown.get(i);
			for (int j = 0; j < chickens.size(); j++) {
				Chicken chick = chickens.get(j);
				if (colliding(temp.getX(), temp.getY(), temp.getWidth(),
						chick.getX(), chick.getY(), chick.getWidth())) {
					chickens.get(j).die();
					chickens.remove(j);
					items.add(new Item(LoadedItems.ITEMLIST.get(0), (temp.getX() / 32) *32, (temp.getY() / 32) * 32, true));
					thrown.remove(i);
					sm.gainXP(3, 30);//gain hunting xp
				}
			}
		}

		cornerX = temp.getX() - 320;
		cornerY= temp.getY() - 240;

		if (temp.getX() < 320) {
			cornerX = 0;
		}

		if (temp.getX() > 31 * 64 - 320 + 32) {
			cornerX = 31 * 64 - 640 + 32;
		}

		if (temp.getY() < 240) {
			cornerY = 0;
		}

		if (temp.getY() > 31 * 64 - 240 + 32) {
			cornerY = 31 * 63 - 480 + 64;
		}

		if (!paused) {
			if (handler.keys[KeyEvent.VK_CONTROL] && handler.keys[KeyEvent.VK_S]) {
				handler.keys[KeyEvent.VK_CONTROL] = handler.keys[KeyEvent.VK_S] = false;
				System.out.println("Save not yet implemented.");
			}
		}

	}

	private boolean colliding(int x1, int y1, int height1, int width1,
			int x2, int y2, int height2, int width2) {
		if ((x2 >= x1 - width2 && x2 <= x1 + width1) && (y2 >= y1 - height2 && y2 <= y1 + height1)) {
			return true;
		}

		if ((x1 >= x2 - width1 && x1 <= x2 + width2) && (y1 >= y2 - height1 && y1 <= y2 + height2)) {
			return true;
		}

		return false;
	}

	private boolean colliding(int x1, int y1, int width1, int x2, int y2, int width2) {//xoverlap && overlap
		return colliding(x1, y1, width1, width1, x2, y2, width2, width2);	
	}

	private boolean mouseIntersect(int ex, int ey, int x, int y, int width) {
		return mouseIntersect(ex, ey, x, y, width, width);
	}

	private boolean mouseIntersect(int ex, int ey, int x, int y, int width, int height) {
		int mx = ex + cornerX;
		int my = ey + cornerY;

		if ((mx > x && mx < x + width) && (my > y && my < y + height)) {
			return true;
		}

		return false;
	}

	@Override
	public void leftClick(int x, int y) {
		int mx = x + cornerX;
		int my = y + cornerY;
		if (!paused) {
			if (mx > temp.getX() - 16 && mx < temp.getX() + 64 + 16 &&
					my > temp.getY() - 16 && my < temp.getY() + 64 + 16) {
				for (int i = 0; i < items.size(); i++) {
					Item item = items.get(i);
					if (colliding(temp.getX(), temp.getY(), temp.getHeight(), temp.getWidth(), item.getX(), item.getY(), item.getWidth(), item.getWidth())) {
						temp.addItem(items.get(i));
						items.remove(i);
						sm.gainXP(6, 30);//gain gathering xp
						break;
					}
				}

				for (int i = 0; i < bushes.size(); i++) {
					Bush bush = bushes.get(i);
					bush.tick();

					if (colliding(temp.getX(), temp.getY(), temp.getHeight(), temp.getWidth(), bush.getX(), bush.getY(), bush.getWidth(), bush.getWidth())) {
						if (bush.hasBerries() && (temp.getInventory()[temp.getPointer()] == null || temp.getInventory()[temp.getPointer()].getID() != 2)) {
							temp.addItem(new Item(LoadedItems.ITEMLIST.get(3),0,0, true));//add item types instead
							bush.pick();
							//fix this, not updateing bush... break somewhere
							sm.gainXP(1, 30);//gain foraging xp
						} else if (bush.isGrown() && temp.getInventory()[temp.getPointer()] != null && temp.getInventory()[temp.getPointer()].getID() == 2) {
							if (bush.hasBerries()) {
								temp.addItem(new Item(LoadedItems.ITEMLIST.get(3),0,0, true));//add item types instead
								bush.pick();
							}
							temp.addItem(new Item(LoadedItems.ITEMLIST.get(1),0,0, true));//add item types instead
							bush.chop();
							sm.gainXP(1, 30);//gain foraging xp
						}
					}
				}

			} 

			for (int i = 0; i < blocks.size(); i++) {
				SolidBlock block = blocks.get(i);
				if (colliding(temp.getX(), temp.getY(), temp.getHeight(), temp.getWidth(), block.getX(), block.getY(), block.getHeight(), block.getWidth()) 
						&& mouseIntersect(x, y, block.getX(), block.getY(), block.getWidth())) {
					if (temp.getInventory()[temp.getPointer()] != null && temp.getInventory()[temp.getPointer()].getID() == 5) {
						temp.addItem(new Item(LoadedItems.ITEMLIST.get(10), 0, 0, true));
						sm.gainXP(2, 30);//gain fishing xp
					}
				}
			}

			for (int i = 0; i < AIS.size(); i++) {
				TempAI ai = AIS.get(i);
				if (colliding(temp.getX(), temp.getY(), temp.getHeight(), temp.getWidth(), ai.getX(), ai.getY(), ai.getHeight(), ai.getWidth()) 
						&& mouseIntersect(x, y, ai.getX(), ai.getY(), ai.getWidth(), ai.getHeight())) {
					if (temp.getInventory()[temp.getPointer()] != null) { 
						if ((temp.getInventory()[temp.getPointer()].getID() == 22 || 
								temp.getInventory()[temp.getPointer()].getID() == 23)) {
							temp.removeItem(temp.getPointer());
							ai.tame();
						}
					}
				}
			}


			sm.leftClick(x, y);


			for (int i = 0; i < AIS.size(); i++) {
				TempAI AI = AIS.get(i);
				if (mouseIntersect(x, y, AIS.get(i).getX(), AIS.get(i).getY(), AIS.get(i).getWidth(), AIS.get(i).getHeight())
						|| (AI.isRect() && mouseIntersect(x, y, AIS.get(i).getX() - 6, AIS.get(i).getY() - 36 - 6, 128 + 6, 48 + 6))) {
					AI.leftClick(x, y);
				} 
			}

			for (int i = 0; i < fires.size(); i++) {
				TempFire fire = fires.get(i);
				if (mouseIntersect(x, y, fire.getX(), fire.getY(), fire.getWidth()) && 
						colliding(temp.getX(), temp.getY(), temp.getHeight(), temp.getWidth(),
								fire.getX(), fire.getY(), fire.getWidth(), fire.getWidth())) {
					if (temp.getInventory()[temp.getPointer()] != null) {
						if (temp.getInventory()[temp.getPointer()].getID() == 9) {
							temp.removeItem(temp.getPointer());
							temp.addItem(new Item(LoadedItems.ITEMLIST.get(22), 0 ,0, true));
							//add cooking experience
							sm.gainXP(0, 30);
						} else if (temp.getInventory()[temp.getPointer()].getID() == 10) {
							temp.removeItem(temp.getPointer());
							temp.addItem(new Item(LoadedItems.ITEMLIST.get(23), 0 ,0, true));
							sm.gainXP(0, 30);
						}
					}
				}
			}
		} else {
			if (mouseIntersect(x - cornerX, y - cornerY, 215, 100, 250, 38)) {
				//handler.setGameState(new MenuState(handler));
				currentState = new MenuState(handler);
			} else if (mouseIntersect(x - cornerX, y - cornerY, 215, 150, 250, 38)) {
				System.out.println("Not yet Implmented");
			} else if (mouseIntersect(x - cornerX, y - cornerY, 215, 200, 250, 38)) {
				paused = false;
			}
		}
	}

	@Override
	public void rightClick(int x, int y) {
		if (!paused) {
			sm.rightClick(x, y);
		}
	}

	@Override
	public void moveWheel(int rot) {
		if (!paused) {
			sm.takeWheel(rot);
		}
	}

	@Override
	public void mouseMove(int x, int y) {
		if (!paused) {
			for (int i = 0; i < items.size(); i++) {//update mouse x and mouse y based on offsets when camera moves
				if (mouseIntersect(x, y, items.get(i).getX(), items.get(i).getY(), items.get(i).getWidth())) {
					items.get(i).setRect();
				} else {
					items.get(i).unRect();
				}
			}

			for (int i = 0; i < AIS.size(); i++) {
				TempAI AI = AIS.get(i);
				if (mouseIntersect(x, y, AIS.get(i).getX(), AIS.get(i).getY(), AIS.get(i).getWidth(), AIS.get(i).getHeight())
						|| (AI.isRect() && mouseIntersect(x, y, AIS.get(i).getX() - 6, AIS.get(i).getY() - 36 - 6, 128 + 6, 48 + 6))) {
					AIS.get(i).setRect();
				} else {
					AIS.get(i).unRect();

				}
			}
		} else {
			mainMenu.unhighlight();//highlighed = slected button, do action on selected button on release??
			settings.unhighlight();
			cancel.unhighlight();
			if (mouseIntersect(x - cornerX, y - cornerY, 215, 100, 250, 38)) {
				mainMenu.highlight();
			} else if (mouseIntersect(x - cornerX, y - cornerY, 215, 150, 250, 38)) {
				settings.highlight();
			} else if (mouseIntersect(x - cornerX, y - cornerY, 215, 200, 250, 38)) {
				cancel.highlight();
			}
		}
	}

}
