package vistas2.ventanas;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import vistas2.ScalablePane;

public class VentanaMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6641141523429026335L;

	public VentanaMenu() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		

		URL url = this.getClass().getResource("/assets/background.png");
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);;
		try {
			image = ImageIO.read(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		ScalablePane fondo = new ScalablePane(image,false);
		
		fondo.setLayout(new BorderLayout());
		this.add(fondo);
		
		this.setContentPane(fondo);
		
		this.setSize(800, 600);
		this.setLocationRelativeTo( null );
		this.setTitle("AlgoCraft");
		this.setVisible(true);
	}
}
