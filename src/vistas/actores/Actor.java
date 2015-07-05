package vistas.actores;

import java.awt.Graphics;
import java.util.Vector;

import juego.interfaces.Controlable;
import vistas.acciones.pendientes.AccionPendiente;

public abstract class Actor {
	
	protected String nombre;
	protected Vector<AccionPendiente> acciones;
	protected PosicionEnCelda posicionCelda = PosicionEnCelda.TIERRA;
	protected Object elemento;
	
	
	public Actor() {
		this.acciones = new Vector<AccionPendiente>();
	}
	
	public Controlable getControlable() { return ((Controlable)this.elemento); }
	
	public abstract void dibujar(Graphics g);

	public String nombre() {
		return this.nombre;
	}
	
	public Vector<AccionPendiente> acciones() {
		return this.acciones;
	}
	
	public void asignarEstado(Object elemento) {
		this.elemento = elemento;
	}
	
	public enum PosicionEnCelda {
	    AIRE(0), TIERRA(0.35);
	    
	    private double posicion;
	    
	    private PosicionEnCelda(double posicion) {
			this.posicion = posicion;
		}
	    
	    public double posicion() {
	    	return this.posicion;
	    }
	}
}
