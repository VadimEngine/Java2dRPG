package entites;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import art.Art;

public class LoadedItems {

	//public static ArrayList<Item> itemList = new ArrayList<>();//MAP?
	public static ArrayList<TempItem> ITEMLIST = loadItems(new File("./res/Items.txt"));

			
	public LoadedItems(/*File itemFile, SpriteSheet spriteSheet*/) {}
	
	public static ArrayList<TempItem> loadItems(File file) {
		System.out.println("Items: " + file);
		ArrayList<TempItem> itemlist = new ArrayList<>();		
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] split = line.split(",");
				int ID = Integer.parseInt(split[0]);
				String itemName = split[1];
				int spriteX = Integer.parseInt(split[2]);
				int spriteY = Integer.parseInt(split[3]);
				String comment = split[4];
				TempItem item = new TempItem(ID, itemName, Art.SPRITES[spriteY][spriteX], comment);

				itemlist.add(item);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return itemlist;
	}

}