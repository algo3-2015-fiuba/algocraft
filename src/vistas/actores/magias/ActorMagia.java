package vistas.actores.magias;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import juego.magias.Magia;
import vistas.actores.Actor;
import vistas.mapa.VistaCelda;
import vistas.utilidades.CacheImagenes;

public class ActorMagia extends Actor {

	protected URL urlMagia;
	
	@SuppressWarnings("unused")
	@Override
	public void dibujar(Graphics g) {

		int altoImagen = (int) (VistaCelda.lado * 0.3);

		Magia magia = (Magia) this.elemento;

		if (this.urlMagia != null) {
			
			Image image;
			try {
				image = CacheImagenes.getInstance().getImage(this.urlMagia, altoImagen);
				g.drawImage(image, (int) (0), (int) (altoImagen * 0.6), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*
			try {
				BufferedImage image;
				image = ImageIO.read(this.urlTormenta);
				//Image scaled = image.getScaledInstance(altoImagen, altoImagen,
						//Image.SCALE_SMOOTH);
				g.drawImage(image, (int) (0), (int) (altoImagen * 0.6), null);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}
