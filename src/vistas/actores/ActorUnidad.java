package vistas.actores;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import vistas.Aplicacion;
import vistas.mapa.VistaCelda;

public class ActorUnidad extends Actor {
	
	protected String name;

	@Override
	public void dibujar(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.white);
		g.setFont(Aplicacion.fontBebas(20f));
		
		int lado = VistaCelda.lado;
		int alto = (int) (lado * 0.4);
		
	    g.drawString(this.name, 0, alto);
	}

}
