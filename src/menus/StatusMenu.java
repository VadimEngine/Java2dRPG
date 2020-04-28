package menus;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;

import art.Art;
import entites.Bush;
import entites.GameMob;
import entites.Item;
import entites.LoadedItems;
import entites.TempFire;
import entites.ThrownRock;
import other.Handler;
import states.PlayState;

public class StatusMenu {

	private int cornerX = 0;
	private int cornerY = 0;

	private GameMob player;
	private int tempWidth = 45;
	private int pointer = 0;
	private Item[] inventory;

	private CraftingWindow cw;
	private boolean cwView;
	private SkillWindow sw;
	private boolean skillView;
	
	private SelectFocusMenu sfm;

	public StatusMenu(GameMob player) {
		this.player = player;
		inventory = player.getInventory();
		cw = new CraftingWindow(player.getX() - 320 + 100, player.getY() - 240 + 100, inventory, new File("./res/Recipies.txt"), player);
		sw = new SkillWindow(player.getX() - 320 + 100, player.getY() - 240 + 100, player, new File("./res/Skills.txt"));
		//sfm = new SelectFocusMenu(PlayState.AIS.get(0), 200, 200);
		sfm = new SelectFocusMenu(200, 200);
	}

	public void tick(Handler handler) {
		cornerX = player.getX() - 320;
		cornerY = player.getY() - 240;

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

		if (handler.keys[KeyEvent.VK_LEFT]) {
			handler.keys[KeyEvent.VK_LEFT] = false;
			moveLeft();
		}

		if (handler.keys[KeyEvent.VK_RIGHT]) {
			handler.keys[KeyEvent.VK_RIGHT] = false;
			moveRight();
		}

		if (handler.keys[KeyEvent.VK_O]) {
			handler.keys[KeyEvent.VK_O] = false;
			player.dropItem(pointer);
		}

		if (handler.keys[KeyEvent.VK_SPACE]) {
			handler.keys[KeyEvent.VK_SPACE] = false;
			for (int i = 0; i < inventory.length; i++) {
				if (inventory[i]!= null && inventory[i].getID() == 0) {
					player.removeItem(i);
					PlayState.thrown.add(new ThrownRock(player.getX(), player.getY() + 32, player.getDir()));
					break;
				}
			}
		}

		cw.setX(cornerX + 100);
		cw.setY(cornerY + 100);

		sw.setX(cornerX + 100);
		sw.setY(cornerY + 100);
		if (sfm == null && PlayState.AIS.size() >= 1) {
			//sfm = new SelectFocusMenu(PlayState.AIS.get(0), 200, 200);
		}
		
		if (sfm != null) {
			sfm.setX(cornerX + 493);
			sfm.setY(cornerY + 280); 
			for (int i = 0; i < PlayState.AIS.size(); i++) {
				if (PlayState.AIS.get(i).isRect()) {
				sfm.setAI(PlayState.AIS.get(i));
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);


		g.fillRect(cornerX + 8, cornerY + 380, 635, 100);

		g.setColor(Color.BLACK);
		g.drawRect(cornerX + 8, cornerY + 380, 635, 100);
		
		g.setColor(Color.RED);
		g.fillRect(cornerX + 16, cornerY + 400, 64, 16);

		g.setColor(Color.GREEN);
		g.fillRect(cornerX + 16, cornerY + 400, (int) ((player.getHealth() / 100.0) * 64.0), 16);

		g.setColor(new Color(200,200,200));


		g.drawRect(cornerX + 16, cornerY + 450, 40, 18);
		g.drawString("Use", cornerX + 18, cornerY + 465);

		for (int i = 0; i < 8; i++) {
			g.fillRect(cornerX + (100) + tempWidth*i, cornerY + 400, tempWidth, tempWidth);
			if (inventory[i] != null) {
				g.drawImage(inventory[i].getImage(), cornerX + (102) + tempWidth*i, cornerY + 400, tempWidth, tempWidth, null);
			}
		}
		g.setColor(Color.BLACK);

		for (int i = 0; i < 8; i++) {
			g.drawRect(cornerX + (100) + tempWidth*i, cornerY + 400, tempWidth, tempWidth);
		}

		g.setColor(Color.YELLOW);

		g.drawRect(cornerX + 100  + (pointer * tempWidth), cornerY+ 400, tempWidth, tempWidth);

		g.setColor(new Color(200,200,200));
		g.drawRect(cornerX +  470, cornerY + 431, 67, 18);
		g.drawString("Craftings", cornerX +  473, cornerY + 445);
		
		g.drawRect(cornerX +  470, cornerY + 455, 67, 18);
		g.drawString("Skills", cornerX +  473, cornerY + 469);

		if (cwView) {
			cw.render(g);
		}
		if (skillView) {
			sw.render(g);
		}
		sfm.render(g);
	}
	
	public void gainXP(int i, int xp) {
		sw.gainXP(i, xp);
	}


	private void moveLeft() {
		if (pointer > 0) {
			pointer--;
		} else {
			pointer = 7;
		}

		player.setPointer(pointer);
	}

	private void moveRight() {
		if (pointer < 7) {
			pointer++;
		} else {
			pointer = 0;
		}
		player.setPointer(pointer);
	}

	public void takeWheel(int rot) {
		if (rot == -1) {
			moveLeft();
		} else if (rot == 1) {
			moveRight();
		}

	}

	public void leftClick(int x, int y) {

		if (x > 16 && x < 16 + 40
				&& y > 450 && y < 450 + 18) {

			
			System.out.println("Called itemlist");
			
			if (inventory[pointer] != null && inventory[pointer].getID() != 3 && inventory[pointer].getID() != 1) {
				System.out.println("Use: " + inventory[pointer].getName());
				Art.MAP[player.getX()/32 + 1][player.getY()/32 + 2] = new Color(180,100,70);
				player.addItem(new Item(LoadedItems.ITEMLIST.get(4), 0, 0, true));
				sw.gainXP(5, 30);
			}

			if (inventory[pointer] != null && inventory[pointer].getID() == 3) {
				Bush bush = new Bush(player.getX()/ 32 * 32, player.getY()/ 32 * 32);
				bush.chop();
				bush.pick();
				PlayState.bushes.add(bush);
				player.removeItem(pointer);
				sw.gainXP(5, 30);//gain farming xp
			}

			if (inventory[pointer] != null && inventory[pointer].getID() == 1) {
				for (int i = 0; i < inventory.length; i++) {
					if (inventory[i] != null && inventory[i].getID() == 0) {
						PlayState.fires.add(new TempFire(player.getX()/ 32 * 32, player.getY()/ 32 * 32));
						player.removeItem(pointer);
						sw.gainXP(4, 30);//gain crafting xp
						break;
					}
				}

			}

		}

		if (x > 100 && x < 100 + 45 * 8 && y > 400 && y < 400 + 45) {
			if (inventory[(x - 100) / 45] != null) {
				player.dropItem((x - 100) / 45);
			}
		}

		if (x >  470 && x < 470 + 67 && y > 431 && y < 431 + 18) {
			if (!cwView) {
				cwView = true;
			} else {
				cwView = false;
			}
			skillView = false;
		}
		if (cwView) {
			cw.leftClick(x, y);
		}
		
		
		if (x >  470 && x < 470 + 67 && y > 455 && y < 455 + 18) {
			if (!skillView) {
				skillView = true;
			} else {
				skillView = false;
			}
			cwView = false;
		}
		
	}

	public void rightClick(int x, int y) {
		if (x > 100 && x < 100 + 45 * 8 && y > 400 && y < 400 + 45) {
			if (inventory[(x - 100) / 45] != null) {
				System.out.println(inventory[(x - 100) / 45].getComment());
			}
		}

	}

}
