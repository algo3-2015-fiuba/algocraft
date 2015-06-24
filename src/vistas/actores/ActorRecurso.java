package vistas.actores;

import java.awt.Color;
import java.awt.Graphics;

import vistas.mapa.VistaCelda;

public class ActorRecurso extends Actor {
	
	protected Color color = Color.white;

	@Override
	public void dibujar(Graphics g) {
		g.setColor(color);
		
		int lado = VistaCelda.lado;		
		int alto = (int)(lado*0.3);
		
	    g.fillRect(0, 0, lado, alto);
	}

}
