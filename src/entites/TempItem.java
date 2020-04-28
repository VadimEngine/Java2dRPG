package entites;

import java.awt.image.BufferedImage;

public class TempItem {
	private int ID;
	private String name;
	private BufferedImage image;
	private String comment;

	public TempItem(int ID, String name, BufferedImage image, String comment) {
		this.ID = ID;
		this.name = name;
		this.image = image;
		this.comment = comment;
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
