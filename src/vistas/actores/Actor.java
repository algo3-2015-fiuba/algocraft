package vistas.actores;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JLabel;

import vistas.acciones.AccionPendiente;

public abstract class Actor {
	
	protected String nombre;
	
	public abstract void dibujar(Graphics g);

	public String nombre() {
		return this.nombre;
	}
	
	public abstract Vector<AccionPendiente> acciones();
}
