package art;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ImageLoader {

	public static BufferedImage load(String file) {
		System.out.println(file);
		System.out.println( );
		BufferedImage image = null;
		try {			
			//image = ImageIO.read(new File("./res/" + file));//classLoader.getResource(fileName).getFile()
			//image = ImageIO.read(new File(ImageLoader.class.getClassLoader().getResource(file).getFile()));
		
			//InputStream input = getClass().getResourceAsStream("/path/to/data.txt");
			
			InputStream input = ImageLoader.class.getClassLoader().getResourceAsStream(file);
			image = ImageIO.read(input);
			
			ImageIcon icon = new ImageIcon(image);
			
			JOptionPane.showMessageDialog(null, file, "Message", JOptionPane.INFORMATION_MESSAGE ,icon);
		
		
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		return image;
	}
}
