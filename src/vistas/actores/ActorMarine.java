package vistas.actores;

import java.awt.Color;
import java.awt.Graphics;

public class ActorMarine extends Actor {

	@Override
	public void dibujar(Graphics g) {
		g.setColor(Color.cyan);
	    g.drawRect (20, 20, 10, 10);
	}

}
