package menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import art.Art;
import entites.GameMob;

public class SkillWindow {
	
	private int x;
	private int y;
	private int height = 260;
	private int width = 200;
	//private GameMob player;
	
	private ArrayList<Skill> skills = new ArrayList<>();
	
	public SkillWindow(int x, int y, GameMob player, File skills) {
		this.x = x;
		this.y = y;
		//this.player = player;
		try {
			Scanner scanner = new Scanner(skills);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] split = line.split(",");
				int skillX = Integer.parseInt(split[1]);
				int skillY = Integer.parseInt(split[2]);
				this.skills.add(new Skill(split[0], skillX, skillY));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x - 1, y - 1, width + 2, height + 2);
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString("Skills", x, y + 16);
		for (int i = 0; i < skills.size(); i++) {
			Skill temp = skills.get(i);
			g.drawImage(temp.image, x, y + 20 + 33 * i, 32, 32, null);
			g.setColor(Color.WHITE);
			g.drawString(temp.getName() + ": " + temp.getLevel(), x + 35, y + 36 + 33 * i);
			g.setColor(Color.BLACK);
			g.fillRect(x +35 , y + 38 + 33 * i, 64, 16);
			g.setColor(Color.YELLOW);
			g.fillRect(x +35 , y + 38 + 33 * i, (int)((temp.xp % 100) / 100.0 * 64), 16);
		}
	}
	
	public void tick() {
		
	}
	
	public void leftClick(int mx, int yx) {
		
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void gainXP(int i, int xp) {
		System.out.println("gaining " + xp + " " + skills.get(i).name + " experience");
		skills.get(i).xp += xp;
	}
	
	class Skill {
		
		private String name;
		private BufferedImage image;
		private int xp = 0;
		
		public Skill(String name, int x, int y) {
			this.name = name;
			this.image = Art.SPRITES[x][y];
			
		}
		
		public String getName() {
			return name;
		}
		
		public BufferedImage getImage() {
			return image;
		}
		
		public int getXp() {
			return xp;
		}
		
		public int getLevel() {
			return xp / 100 + 1;
		}
		
		public void gainXp(int xp) {
			this.xp += xp;
		}
		
		
	}

}
