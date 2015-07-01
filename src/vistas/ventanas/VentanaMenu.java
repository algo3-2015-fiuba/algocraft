package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import vistas.utilidades.ScalablePane;

public class VentanaMenu extends JFrame {
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
