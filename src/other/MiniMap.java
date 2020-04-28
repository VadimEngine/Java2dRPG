package other;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import art.Art;
import entites.GameMob;
import entites.TempAI;
import states.PlayState;
import testingEnt.Player;

public class MiniMap {

	private BufferedImage image;
	private GameMob player;
	
	private Player testPlayer;
	
	int cornerX = 0;
	int cornerY = 0;

	public MiniMap(Color[][] map, GameMob player) {
		image = new BufferedImage(map[0].length, map.length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				image.setRGB(i, j, map[i][j].getRGB());
			}
		}

		this.player = player;

	}
	
	public MiniMap(Color[][] map, Player player) {
		image = new BufferedImage(map[0].length, map.length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				image.setRGB(i, j, map[i][j].getRGB());
			}
		}

		this.testPlayer = player;

	}

	public void tick() {
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

		for (int i = 0; i < Art.MAP.length; i++) {
			for (int j = 0; j < Art.MAP[0].length; j++) {
				image.setRGB(i, j, Art.MAP[i][j].getRGB());
			}
		}

		if (player.getX() / 32 > 0 && player.getX() / 32 < Art.MAP[0].length - 1 
				&& player.getY() / 32 > 0 && player.getY() / 32 < Art.MAP.length - 1) {
			image.setRGB(player.getX() / 32, player.getY() / 32, 0xFF0000);
			image.setRGB(player.getX() / 32 - 1, player.getY() / 32 - 1, 0xFF0000);
			image.setRGB(player.getX() / 32 - 1, player.getY() / 32, 0xFF0000);
			image.setRGB(player.getX() / 32, player.getY() / 32 - 1, 0xFF0000);
		} 

		for (int i = 0; i < PlayState.AIS.size(); i++) {
			TempAI temp = PlayState.AIS.get(i);
			if (temp.getX() / 32 > 0 && temp.getX() / 32 < Art.MAP[0].length - 1 
					&& temp.getY() / 32 > 0 && temp.getY() / 32 < Art.MAP.length - 1) {
				if (temp.isRect()) {
					image.setRGB(temp.getX() / 32, temp.getY() / 32, 0xFFFF00);
					image.setRGB(temp.getX() / 32 - 1, temp.getY() / 32 - 1, 0xFFFF00);
					image.setRGB(temp.getX() / 32 - 1, temp.getY() / 32, 0xFFFF00);
					image.setRGB(temp.getX() / 32, temp.getY() / 32 - 1, 0xFFFF00);
				} else {

					image.setRGB(temp.getX() / 32, temp.getY() / 32, 0x0000FF);
					image.setRGB(temp.getX() / 32 - 1, temp.getY() / 32 - 1, 0x0000FF);
					image.setRGB(temp.getX() / 32 - 1, temp.getY() / 32, 0x0000FF);
					image.setRGB(temp.getX() / 32, temp.getY() / 32 - 1, 0x0000FF);
				}
			}
		}

	}
	
	public void tick2() {
		for (int i = 0; i < Art.MAP.length; i++) {
			for (int j = 0; j < Art.MAP[0].length; j++) {
				image.setRGB(i, j, Art.MAP[i][j].getRGB());
			}
		}

		if (testPlayer.getX() / 32 > 0 && testPlayer.getX() / 32 < Art.MAP[0].length - 1 
				&& testPlayer.getY() / 32 > 0 && testPlayer.getY() / 32 < Art.MAP.length - 1) {
			image.setRGB(testPlayer.getX() / 32, testPlayer.getY() / 32, 0xFF0000);
			image.setRGB(testPlayer.getX() / 32 - 1, testPlayer.getY() / 32 - 1, 0xFF0000);
			image.setRGB(testPlayer.getX() / 32 - 1, testPlayer.getY() / 32, 0xFF0000);
			image.setRGB(testPlayer.getX() / 32, testPlayer.getY() / 32 - 1, 0xFF0000);
		} 
	}

	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(cornerX + 640 - 128, cornerY + 4, 64 * 2 + 8, 64*2 + 8);
		g.drawImage(image, cornerX + 640 - 128 + 4, cornerY + 8, 64 * 2, 64 * 2, null);
	}
	
	public void render(Graphics g, int cornerX, int cornerY) {
		g.setColor(Color.GRAY);
		g.fillRect(cornerX + 640 - 128, cornerY + 4, 64 * 2 + 8, 64*2 + 8);
		g.drawImage(image, cornerX + 640 - 128 + 4, cornerY + 8, 64 * 2, 64 * 2, null);
	}

}
