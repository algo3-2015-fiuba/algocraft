package vistas.actores.recursos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;

import vistas.Aplicacion;
import vistas.acciones.AccionPendienteUnidad;
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
		
		this.dibujarNombre(g);

	}
	
	private void dibujarNombre(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		g.setFont(Aplicacion.fontBebas(16f));
		
		int lado = VistaCelda.lado;
		int alto = (int) (lado * 0.95);
		
		
		g.setColor(Color.black);
	    g.drawString(this.nombre, 0, alto);
	    
	    g.setColor(Color.black);
	    g.drawString(this.nombre, 2, alto);
	    
	    g.setColor(Color.black);
	    g.drawString(this.nombre, 0, alto-1);
	    
	    g.setColor(Color.black);
	    g.drawString(this.nombre, 0, alto+1);
	    
	    g.setColor(Color.white);
	    g.drawString(this.nombre, 1, alto);
	}
	
	public String nombre() {
		return this.nombre;
	}

}
