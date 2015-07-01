package vistas.actores;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JLabel;

import vistas.acciones.AccionPendiente;
import vistas.acciones.AccionPendienteUnidad;

public abstract class Actor {
	
	protected String nombre;
	protected Vector<AccionPendiente> acciones;
	
	public Actor() {
		this.acciones = new Vector<AccionPendiente>();
	}
	
	public abstract void dibujar(Graphics g);

	public String nombre() {
		return this.nombre;
	}
	
	public Vector<AccionPendiente> acciones() {
		return this.acciones;
	}
}
