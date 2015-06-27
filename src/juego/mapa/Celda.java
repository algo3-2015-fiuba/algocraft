package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Controlable;
import juego.magias.Magia;
import juego.materiales.Material;
import juego.razas.construcciones.Construccion;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.razas.unidades.Unidad;
import juego.recursos.Recurso;

public class Celda {
	
	private Material material;
	private Recurso recurso;
	private Collection<Construccion> construcciones;
	private Collection<Unidad> unidades;
	
	public Celda(Material material, Recurso recurso, Coordenada posicion) {
		
		super();
		this.material = material;
		this.recurso = recurso;
		this.construcciones = new ArrayList<Construccion>();
		this.unidades = new ArrayList<Unidad>();
		
	}
	
	public boolean poseeRecursos() { return (this.recurso != null); }
	public Material getMaterial() { return (this.material); }	
	public Recurso getRecurso() { return (this.recurso); }
	
	public Collection<Unidad> getUnidades() {
		return this.unidades;
	}
	
	public boolean puedeOcuparTierra(Unidad terrestre) {
		
		if (!this.material.equals(Material.tierra)) return false;
		if (this.poseeRecursos()) return false;
		
		if (!this.construcciones.isEmpty()) return false;
		
		Iterator<Unidad> it = this.unidades.iterator();
		while (it.hasNext()) {
				
			if (it.next().colisionaCon(terrestre)) {
				return false;
			}
				
		}
		
		return true;
		
	}
	
	public boolean puedeOcuparAire(Unidad voladora) {
		
		Iterator<Unidad> it = this.unidades.iterator();
		while (it.hasNext()) {
				
			if (it.next().colisionaCon(voladora)) {
				return false;
			}
				
		}
		
		return true;
		
	}
	
	public boolean puedeConstruir(ConstruccionRecolectora construccion) {
		
		if (!this.material.equals(Material.tierra)) return false;

		if (!this.poseeRecursos()) return false;
		
		if (!this.recurso.puedeRecolectar(construccion)) return false;
		
		Iterator<Unidad> itU = this.unidades.iterator();
		while (itU.hasNext()) {
				
			if (itU.next().colisionaCon(construccion)) {
				return false;
			}
				
		}
		
		return (this.construcciones.isEmpty());
		
	}
	
	public boolean puedeConstruir(Construccion construccion) {
		
		if (!this.material.equals(Material.tierra)) return false;
		
		if (this.poseeRecursos()) return false;
		
		Iterator<Unidad> itU = this.unidades.iterator();
		while (itU.hasNext()) {
				
			if (itU.next().colisionaCon(construccion)) {
				return false;
			}
				
		}
		
		return (this.construcciones.isEmpty());
		
	}
	
	public void ocupar(Unidad unidad){	
		if (!this.unidades.contains(unidad)) {
			this.unidades.add(unidad);		
		}
	}
	
	public void ocupar(Construccion construccion) {	
		if (!this.construcciones.contains(construccion)) {
			this.construcciones.add(construccion);
		}
	}
	
	public void desocupar(Unidad unidad) {
		this.unidades.remove(unidad);
	}
	
	public void desocupar(Construccion construccion) {
		this.construcciones.remove(construccion);
	}

	public boolean contiene(Controlable controlable) {
		
		if (this.unidades.contains(controlable)) return true;
		
		return (this.construcciones.contains(controlable));
		
	}

	public void afectadaPorMagia(Magia magia) {
		
		Unidad unidades[] = new Unidad[this.unidades.size()];
		unidades = this.unidades.toArray(unidades);
		
		for(Unidad unidad : unidades) {
			magia.afectar(unidad);
		}
		
	}
	
}
