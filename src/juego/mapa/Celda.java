package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Construible;
import juego.materiales.Material;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;
import juego.recursos.Recurso;

public class Celda {
	
	private Material material;
	private Recurso recurso;
	private Collection<Construible> construibles;
	private Collection<Unidad> unidades;
	
	public Celda(Material material, Recurso recurso, Coordenada posicion) {
		
		super();
		this.material = material;
		this.recurso = recurso;
		this.construibles = new ArrayList<Construible>();
		this.unidades = new ArrayList<Unidad>();
		
	}
	
	public boolean poseeRecursos() { return (this.recurso != null); }
	public Material getMaterial() { return (this.material); }	
	public Recurso getRecurso() { return (this.recurso); }
	
	public boolean puedeOcuparTierra(Unidad terrestre) {
		
		if (!this.material.equals(Material.tierra)) return false;
		if (this.poseeRecursos()) return false;
		
		if (!this.construibles.isEmpty()) return false;
		
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
	
	public boolean puedeConstruir(Construccion construccion) {
		
		if (!this.material.equals(Material.tierra)) return false;

		Iterator<Unidad> itU = this.unidades.iterator();
		while (itU.hasNext()) {
				
			if (itU.next().colisionaCon(construccion)) {
				return false;
			}
				
		}
		
		return (this.construibles.isEmpty());
		
	}
	
	public void ocupar(Unidad unidad){		
		this.unidades.add(unidad);		
	}
	
	public void ocupar(Construccion construible) {	
		this.construibles.add(construible);
	}
	
	public void desocupar(Unidad unidad) {
		this.unidades.remove(unidad);
	}
	
	public void desocupar(Construible construible) {
		this.construibles.remove(construible);
	}

	public boolean contiene(Unidad unidad) {
		return (this.unidades.contains(unidad));
	}

	public boolean contiene(Construible construible) {
		return (this.construibles.contains(construible));
	}
	
}
