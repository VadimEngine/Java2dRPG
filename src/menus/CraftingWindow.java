package menus;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entites.GameMob;
import entites.Item;
import entites.LoadedItems;
import entites.TempItem;

public class CraftingWindow {
	private int x;
	private int y;
	private int height = 220;
	private int width = 200;
	private Item[] inventory;
	private GameMob player;
	private ArrayList<Recipie> recs = new ArrayList<>();

	public CraftingWindow(int x, int y, Item[] inventory, File crafts, GameMob player) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.inventory = inventory;
		try {
			Scanner scanner = new Scanner(crafts);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine(); 
				String[] split = line.split(",");
				recs.add(new Recipie(split));
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x - 1, y - 1, width + 2, height + 2);
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString("Cratings", x, y + 16);
		for (int i = 0; i < recs.size(); i++) {
			Recipie temp = recs.get(i);
			g.drawRect(x, y + 20 + 33 * i, 33, 33);
			g.drawImage(temp.main.getImage(), x, y + 20 + (34 * i), 32, 32, null);
			for (int j = 0; j < temp.rec.length; j++) {
				g.drawImage(temp.rec[j].getImage(), x + 34 * (j + 1), y + 20 + 34 * i, 32, 32, null);
			}
		}
	}

	public void leftClick(int mx, int my) {
		mx += x - 100;
		my += y - 100;
		if (mx > x && mx < x + 33 && my > y + 20 && my < y + 20 + 32  * 6) {
			recAction(recs.get(((my - y - 20)) / 34));
		}
	}
	
	private void recAction(Recipie recipie) {
		for (int i = 0; i < recipie.rec.length; i++) {
			if (!containItem(recipie.rec[i])) {
				return;
			}
		}
		
		for (int i = 0; i < recipie.rec.length; i++) {
			player.removeItem(recipie.rec[i]);
		}
		player.addItem(new Item(recipie.main, 0, 0, true));
		//gain crafting xp
	}
	
	public boolean containItem(TempItem item) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getID() == item.getID()) {
				return true;
			}
		}
		return false;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	class Recipie {

		private TempItem main;
		private TempItem[] rec;

		public Recipie(String[] ids) {
			main = LoadedItems.ITEMLIST.get(Integer.parseInt(ids[0]));
			rec = new TempItem[ids.length - 1];
			for (int i = 0; i < rec.length; i++) {
				rec[i] = LoadedItems.ITEMLIST.get(Integer.parseInt(ids[i+1]));
			}
		}

		public void printRec() {
			System.out.print(main.getName() + ": ");
			for (int i = 0; i < rec.length; i++) {
				System.out.print(rec[i].getName() + ", ");
				if (i == rec.length - 1) {
					System.out.print("\n");
				}
			}
		}

	}

}
