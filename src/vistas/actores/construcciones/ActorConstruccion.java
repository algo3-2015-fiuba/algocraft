package vistas.actores.construcciones;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import juego.interfaces.Construible;
import vistas.Aplicacion;
import vistas.actores.ActorControlable;
import vistas.mapa.VistaCelda;

public class ActorConstruccion extends ActorControlable {
	
	protected URL urlConstruccion;
	
	public ActorConstruccion() {
		this.nombre = "Construccion";
		this.urlConstruccion = Aplicacion.class.getResource("/assets/iconos/notificaciones/en construccion_small.png");
	}
	
	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
		
		int altoImagen = (int)(VistaCelda.lado * 0.35);
		
		Construible construccion = (Construible)this.elemento;
		
		if(!construccion.construccionFinalizada()) {
			
			if (this.urlConstruccion != null) {
	
				try {
					BufferedImage image;
					image = ImageIO.read(this.urlConstruccion);
					//Image scaled = image.getScaledInstance(altoImagen, altoImagen, Image.SCALE_SMOOTH);
					g.drawImage(image, (int)(0), (int)(VistaCelda.lado*0.1), null);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
