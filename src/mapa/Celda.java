package mapa;

import mapa.excepciones.CeldaNoVacia;
import juego.razas.interfaces.Controlable;

public class Celda {
	
	private Controlable controlable;
	private Material.Materiales base;
	
	public Celda(Material.Materiales base) {
		this.base = base;
		this.controlable = null;
	}

	public void agregarControlable(Controlable Controlable) throws CeldaNoVacia {
		if (this.estaVacia()) {
			this.controlable = Controlable;
		} else {
			throw new CeldaNoVacia();
		}
	}
	
	public Controlable getControlable() {
		return this.controlable;
	}
	
	public Material.Materiales getBase() {
		return this.base;
	}
	
	public void removerControlable() {
		this.controlable = null;
	}
	
	public boolean estaVacia() {
		return (this.controlable == null);
	}
	
	public boolean puedeExistirControlable(Controlable Controlable) {
		return true;
	}
	
	
}
