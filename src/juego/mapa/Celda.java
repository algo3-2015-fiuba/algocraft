package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.bases.Base;
import juego.interfaces.Controlable;
import juego.magias.Magia;
import juego.materiales.Material;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;
import juego.recursos.Recurso;

public class Celda {
	
	private Material material;
	private Recurso recurso;
	private Base base;
	private Coordenada posicion;
	private Collection<Construccion> construcciones;
	private Collection<Unidad> unidades;
	
	public Celda(Material material, Recurso recurso, Coordenada posicion) {
		
		super();
		this.material = material;
		this.recurso = recurso;
		this.posicion = posicion;
		this.construcciones = new ArrayList<Construccion>();
		this.unidades = new ArrayList<Unidad>();
		
	}

	public boolean poseeRecursos() { return (this.recurso != null); }
	public boolean poseeBase() { return (this.base != null); }
	public Material getMaterial() { return (this.material); }	
	public Recurso getRecurso() { return (this.recurso); }
	public Base getBase() { return (this.base); }
	public Coordenada getPosicion() { return this.posicion; }
	
	public Collection<Unidad> getUnidades() {
		return this.unidades;
	}
	
	public void construir(Base base) {
		this.base = base;
	}
	
	public boolean colisiona(Controlable controlable) {
		
		Iterator<Unidad> itUnid = this.unidades.iterator();
		while (itUnid.hasNext()) {
				
			if (controlable.colisionaCon(itUnid.next())) return true;
				
		}
		
		Iterator<Construccion> itConst = this.construcciones.iterator();
		while(itConst.hasNext()) {
			
			if (controlable.colisionaCon(itConst.next())) return true;

		}
		
		return false;
		
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
