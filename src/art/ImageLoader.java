package art;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageLoader {

	public static BufferedImage load(String file) {
		System.out.println(file);
		System.out.println( );
		BufferedImage image = null;
		try {						
			InputStream input = ImageLoader.class.getClassLoader().getResourceAsStream(file);
			image = ImageIO.read(input);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return image;
	}
}