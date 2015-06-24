package vistas.actores.recursos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import vistas.Aplicacion;
import vistas.actores.Actor;
import vistas.mapa.VistaCelda;

public class ActorRecurso extends Actor {

	protected Color color = Color.white;
	protected URL url;

	@Override
	public void dibujar(Graphics g) {
		g.setColor(color);

		int lado = VistaCelda.lado;
		int alto = (int) (lado * 0.3);
		int altoImagen = (int) (lado * 0.3);

		g.fillRect(0, lado - alto, lado, alto);

		if (url != null) {

			try {
				BufferedImage image;
				image = ImageIO.read(url);
				Image scaled = image.getScaledInstance(lado, image.getHeight(), Image.SCALE_SMOOTH);
				g.drawImage(scaled, 0, lado - altoImagen, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
