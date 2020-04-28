package art;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Art {//static constructor

	/**
	 * Array of sprites from the Sprite.png file in res.
	 */
	public static BufferedImage SPRITES[][] = loadImages("Sprites.png");

	public static Color[][] MAP = loadMap(ImageLoader.load("Map.png"));

	public static HashMap<Color, BufferedImage> COLORMAP = loadColorMap(); 
	
	private static int count = 0;


	/**
	 * 
	 * @param file file name in the res folder(needs better organization and loading)
	 * @return
	 */
	private static BufferedImage[][] loadImages(String file) {
		BufferedImage[][] sprites = new BufferedImage[16][16];
		BufferedImage image = ImageLoader.load(file);
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				sprites[i][j] = cleanImage(image.getSubimage(j * 16, i * 16, 16, 16));
			}
		}
		return sprites;
	}

	private static Color[][] loadMap(BufferedImage image) {
		Color[][] map = new Color[64][64];
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				map[i][j] = new Color(image.getRGB(i, j));
			}
		}

		return map;
	}

	private static HashMap<Color, BufferedImage> loadColorMap() {
		HashMap<Color, BufferedImage> colorMap = new HashMap<>();
		colorMap.put(new Color(0,255,0), Art.SPRITES[1][0]);
		colorMap.put(new Color(0,155,0), Art.SPRITES[1][1]);
		colorMap.put(new Color(0,0,255), Art.SPRITES[1][2]);
		colorMap.put(new Color(255,255,0), Art.SPRITES[1][3]);
		colorMap.put(new Color(255,255,255), Art.SPRITES[1][4]);
		colorMap.put(new Color(155,155,155), Art.SPRITES[1][5]);
		colorMap.put(new Color(135,90,30), Art.SPRITES[1][6]);
		colorMap.put(new Color(180,100,70), Art.SPRITES[1][15]);
		return colorMap;
	}

	/**
	 * Returns a 8 bit RGBA if there are alpha pixel, else returns the given image(indexed)
	 * @param image an image that may or may not have pixels with (255,0,255) or (200, 0, 200). 
	 * @return
	 */
	private static BufferedImage cleanImage(BufferedImage image) {
		boolean hasAlpha = false;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);//
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				if (image.getRGB(j, i) != 0xFFFF00FF && image.getRGB(j, i) != 0xFFC800C8) {
					newImage.setRGB(j, i, image.getRGB(j, i));
				} else {
					hasAlpha = true;
				}
			}
		}
		if (hasAlpha) {
			return newImage;
		} else {
			return image;
		}
	}


}
