package vistas.actores;

import java.awt.Graphics;

public abstract class Actor {
	
	protected String nombre;
	
	public abstract void dibujar(Graphics g);

	public String nombre() {
		return this.nombre;
	}
}
