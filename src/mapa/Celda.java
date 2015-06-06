package mapa;

import mapa.excepciones.CeldaNoVacia;
import juego.razas.interfaces.Controlable;

public class Celda {
	
	private Controlable controlableAire;
	private Controlable controlableTierra;
	private Material.Materiales base;
	private Recurso recurso;
	
	public Celda(Material.Materiales base, Recurso recurso) {
		this.base = base;
		this.controlableAire = null;
		this.controlableTierra = null;
		this.recurso = recurso;
	}

	public void agregarControlableEnAire(Controlable Controlable) throws CeldaNoVacia {
		if (!this.ocupadoEnAire()) {
			this.controlableAire = Controlable;
		} else {
			throw new CeldaNoVacia();
		}
	}
	
	public void agregarControlableEnTierra(Controlable Controlable) throws CeldaNoVacia {
		if (!this.ocupadoEnTierra()) {
			this.controlableTierra = Controlable;
		} else {
			throw new CeldaNoVacia();
		}
	}
	
	public void agregarRecurso(Recurso nuevoRecurso) {
		this.recurso = nuevoRecurso;
	}
	
	public Material.Materiales materialBase() {
		return this.base;
	}
	
	public Controlable obtenerControlableEnAire() {
		return this.controlableTierra;
	}
	
	
	public Controlable obtenerControlableEnTierra() {
		return this.controlableTierra;
	}
	
	public Recurso obtenerRecurso() {
		return this.recurso;
	}
	
	public boolean ocupadoEnAire() {
		return (this.controlableAire != null);
	}
	
	public boolean ocupadoEnTierra() {
		return (this.controlableTierra != null);
	}
	
	public boolean puedeExistirControlable(Controlable Controlable) {
		return true;
	}
	
	public void removerControlableEnAire() {
		this.controlableAire = null;
	}

	public void removerControlableEnTierra() {
		this.controlableTierra = null;
	}
	
	
}
