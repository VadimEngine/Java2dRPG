package menus;

import java.awt.Color;
import java.awt.Graphics;

import testingEnt.ItemType;
import testingEnt.Player;

public class StatusMenu2 extends AbstractMenu{

	private Player player;
	private int tempWidth = 48;

	private ItemType[] inventory;
	
	private SelectFocusMenu sfm;
	
	private TempButton skillButton = new TempButton("Skills", 12);
	private TempButton craftButton = new TempButton("Craft", 12);

	public StatusMenu2(Player player, int width, int height, int bufferSpace, boolean visible) {
		super(width, height, bufferSpace, visible);
		this.player = player;
		inventory = player.getInventory();
		//static size, default size
	}
	
	public StatusMenu2(Player player) {
		super(48 * 8 + 128, 128, 4, true);
		this.player = player;
		inventory = player.getInventory();
	}

	public void tick() {//tick and pass the hander keys/mouse clicks?

	}

	public void render(Graphics g, int cornerX, int cornerY) {
		g.setColor(Color.LIGHT_GRAY);

		g.fillRect(cornerX, cornerY, width, height);
		
		g.setColor(Color.BLACK);
		for (int i = 0; i < 8; i++) {
			g.drawRect(cornerX + tempWidth * i - 1, cornerY, tempWidth, tempWidth);
			if (inventory[i] != null) {
				g.drawImage(inventory[i].getImage(),cornerX + tempWidth * i, cornerY, tempWidth, tempWidth, null);
			}
		}
		
		skillButton.render(g, cornerX, cornerY + 50);
		craftButton.render(g, cornerX, cornerY + 80);
	}

	public void leftClick(int x, int y) {
		if (x > 100 && x < 100 + 45 * 8 && y > 400 && y < 400 + 45) {
			if (inventory[(x - 100) / 45] != null) {
				player.dropItem((x - 100) / 45);
				System.out.println("Drop item sm2");
			}
		}
	}


}
