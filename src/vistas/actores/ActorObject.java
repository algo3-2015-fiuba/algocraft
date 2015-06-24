package vistas.actores;

import java.awt.Color;
import java.awt.Graphics;

public class ActorObject extends Actor {

	@Override
	public void dibujar(Graphics g) {
		g.setColor(Color.white);
	    g.drawRect (20, 20, 10, 10);
	}

}
