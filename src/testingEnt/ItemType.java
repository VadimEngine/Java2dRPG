package testingEnt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import art.Art;
import art.ImageLoader;

public class ItemType {
	
	//public static ArrayList<ItemType> ITEMLIST = loadItems(new File("./res/Items.txt"));
	public static ArrayList<ItemType> ITEMLIST = loadItems("Items.txt");
	
	private int ID;
	private String name;
	private BufferedImage image;
	private String comment;

	public ItemType(int ID, String name, BufferedImage image, String comment) {
		this.ID = ID;
		this.name = name;
		this.image = image;
		this.comment = comment;
	}
	
	private static ArrayList<ItemType> loadItems(String file) {
		
		System.out.println(file);
		
		ArrayList<ItemType> itemlist = new ArrayList<>();
		
		//Scanner scanner = new Scanner(file);
		Scanner scanner = new Scanner(ImageLoader.class.getClassLoader().getResourceAsStream(file));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] split = line.split(",");
			int ID = Integer.parseInt(split[0]);
			String itemName = split[1];
			int spriteX = Integer.parseInt(split[2]);
			int spriteY = Integer.parseInt(split[3]);
			String comment = split[4];
			ItemType item = new ItemType(ID, itemName, Art.SPRITES[spriteY][spriteX], comment);

			itemlist.add(item);
		}
		scanner.close();
	
		
		return itemlist;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public String getCommet() {
		return comment;
	}



}
