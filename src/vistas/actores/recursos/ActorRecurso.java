package vistas.actores.recursos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;

import vistas.Aplicacion;
import vistas.actores.Actor;
import vistas.mapa.VistaCelda;
import vistas.utilidades.CacheImagenes;

public class ActorRecurso extends Actor {

	protected Color color = Color.white;
	protected URL url;

	@SuppressWarnings("unused")
	@Override
	public void dibujar(Graphics g) {
		

		int lado = VistaCelda.lado;
		int alto = (int) (lado * 0.3);
		int altoImagen = (int) (lado * 0.3);

		g.fillRect(0, lado - alto, lado, alto);

		if (url != null) {

			try {
				Image scaled = CacheImagenes.getInstance().getImage(this.url, lado);
				g.drawImage(scaled, 0, 0, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*g.setColor(new Color(0,0,0,100));
		g.fillRect(0, 0, lado, lado);*/
		
		//this.dibujarNombre(g);

	}
	
	@SuppressWarnings("unused")
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
