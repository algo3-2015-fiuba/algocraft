package juego.mapa;

import juego.interfaces.Controlable;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.materiales.Material;
import juego.recursos.Recurso;

public class Celda {
	
	private Controlable controlableAire;
	private Controlable controlableTierra;
	private Material material;
	private Recurso recurso;
	
	public Celda(Material material, Recurso recurso) {
		this.material = material;
		this.recurso = recurso;
		this.controlableAire = null;
		this.controlableTierra = null;
	}

	public void agregarControlable(Controlable controlable) throws CeldaOcupada {
		controlable.ocuparCelda(this);
	}
	
	public void ocuparTierra(Controlable controlable) throws CeldaOcupada { 
		if (!this.ocupadoEnTierra()) {
			this.controlableTierra = controlable; 
		} else {
			throw new CeldaOcupada();
		}
	}
	
	public void ocuparAire(Controlable controlable) throws CeldaOcupada {
		if (!this.ocupadoEnAire()) {
			this.controlableAire = controlable; 
		} else {
			throw new CeldaOcupada();
		}
	}
	
	public void agregarRecurso(Recurso nuevoRecurso) {
		this.recurso = nuevoRecurso;
	}
	
	public Material obtenerMaterial() {
		return this.material;
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
	
	public void removerControlableEnAire() {
		this.controlableAire = null;
	}

	public void removerControlableEnTierra() {
		this.controlableTierra = null;
	}
	
	
}
