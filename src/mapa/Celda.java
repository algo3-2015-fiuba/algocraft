package mapa;

import mapa.excepciones.CeldaNoVacia;
import juego.razas.interfaces.Controlable;

public class Celda {
	
	private Controlable Controlable;
	private Material.Materiales base;
	
	public Celda(Material.Materiales base) {
		this.base = base;
		this.Controlable = null;
	}

	public void agregarControlable(Controlable Controlable) throws CeldaNoVacia {
		if (this.estaVacia()) {
			this.Controlable = Controlable;
		} else {
			throw new CeldaNoVacia();
		}
	}
	
	public Controlable getControlable() {
		return this.Controlable;
	}
	
	public Material.Materiales getBase() {
		return this.base;
	}
	
	public void removerControlable() {
		this.Controlable = null;
	}
	
	public boolean estaVacia() {
		return (this.Controlable == null);
	}
	
	public boolean puedeExistirControlable(Controlable Controlable) {
		return true;
	}
	
	
}
