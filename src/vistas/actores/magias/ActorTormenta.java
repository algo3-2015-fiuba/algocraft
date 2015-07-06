package vistas.actores.magias;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.jugadores.Jugador;
import juego.magias.Magia;
import vistas.Aplicacion;
import vistas.actores.Actor;
import vistas.actores.ActorControlable;
import vistas.actores.jugadores.ActorJugador;
import vistas.mapa.VistaCelda;
import vistas.utilidades.AsignadorVistas;
import vistas.utilidades.CacheImagenes;

public class ActorTormenta extends ActorMagia {

	protected URL urlTormenta;

	public ActorTormenta() {
		this.urlTormenta = Aplicacion.class
				.getResource("/assets/iconos/protoss/unidades/magias/tormenta psionica.png");
	}

	@Override
	public void dibujar(Graphics g) {

		super.dibujar(g);

		int altoImagen = (int) (VistaCelda.lado * 0.3);

		Magia magia = (Magia) this.elemento;

		if (this.urlTormenta != null) {
			
			Image image;
			try {
				image = CacheImagenes.getInstance().getImage(this.urlTormenta, altoImagen);
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
