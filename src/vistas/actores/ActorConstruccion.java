package vistas.actores;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import vistas.mapa.VistaCelda;

public class ActorConstruccion extends Actor {

	protected URL url;
	
	public ActorConstruccion() {
		this.nombre = "Unidad";
	}
	
	@Override
	public void dibujar(Graphics g) {
		g.setColor(Color.WHITE);

		int lado = VistaCelda.lado;
		int altoImagen = (int) (lado * 0.8);
		int margenXImagen = (int) (lado * 0.1);
		int margenYImagen = (int) (lado * this.posicionCelda.posicion());

		//g.fillRect(0, lado - alto, lado, alto);

		if (url != null) {

			try {
				BufferedImage image;
				image = ImageIO.read(url);
				Image scaled = image.getScaledInstance(altoImagen, altoImagen, Image.SCALE_SMOOTH);
				g.drawImage(scaled, margenXImagen, margenYImagen, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
