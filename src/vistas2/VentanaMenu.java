package vistas2;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class VentanaMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6641141523429026335L;

	public VentanaMenu() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		

		URL url = this.getClass().getResource("/assets/background.png");
		BufferedImage image;
		try {
			image = ImageIO.read(url);
			this.add(new ScalablePane(image,false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setSize(800, 600);
		this.setTitle("AlgoCraft");
		this.setVisible(true);
	}
}
