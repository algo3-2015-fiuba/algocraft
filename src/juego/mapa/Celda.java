package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Controlable;
import juego.interfaces.Observable;
import juego.jugadores.Jugador;
import juego.magias.Magia;
import juego.materiales.Material;
import juego.razas.construcciones.Construccion;
import juego.razas.construcciones.ConstruccionBase;
import juego.razas.unidades.Unidad;
import juego.recursos.Recurso;

public class Celda implements Observable {
	
	private Material material;
	private Recurso recurso;
	private ConstruccionBase base;
	private Coordenada posicion;
	private Collection<Construccion> construcciones;
	private Collection<Unidad> unidades;
	private Collection<Controlable> observadores;
	private Collection<Magia> magiasActivas;
	
	public Celda(Material material, Recurso recurso, Coordenada posicion) {
		
		super();
		this.material = material;
		this.recurso = recurso;
		this.posicion = posicion;
		this.construcciones = new ArrayList<Construccion>();
		this.unidades = new ArrayList<Unidad>();
		this.observadores = new ArrayList<Controlable>();
		this.magiasActivas = new ArrayList<Magia>();
		
	}

	public boolean poseeRecurso() { return (this.recurso != null); }
	public boolean poseeBase() { return (this.base != null); }
	public Material getMaterial() { return (this.material); }	
	public Recurso getRecurso() { return (this.recurso); }
	public ConstruccionBase getBase() { return (this.base); }
	public Coordenada getPosicion() { return this.posicion; }
	
	public Collection<Unidad> getUnidades() {
		return this.unidades;
	}
	
	public Collection<Construccion> getConstrucciones() {
		return this.construcciones;
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
	
	public void ocupar(ConstruccionBase base) {
		this.base = base;
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
		
		if (this.construcciones.contains(controlable)) return true;
		
		if (this.base == null) return false;
		
		return (this.base.equals(controlable));
		
	}
	
	@Override
	public void agregarObservador(Controlable controlable) {
		if (!this.observadores.contains(controlable)) {
			this.observadores.add(controlable);
		}
	}
	
	@Override
	public void removerObservador(Controlable controlable) {
		this.observadores.remove(controlable);
	}
	
	@Override
	public boolean observadaPor(Controlable controlable) {
		return (this.observadores.contains(controlable));
	}
	
	@Override
	public boolean observadaPor(Jugador jugador) {
		
		Iterator<Controlable> it = this.observadores.iterator();
		
		while (it.hasNext()) {
			
			if (jugador.esAliado(it.next())) return true;
			
		}
		
		return false;
	}
	
	public Collection<Magia> getMagiasActivas() {
		return this.magiasActivas;
	}
	
	public void agregarMagia(Magia magia) {
		this.magiasActivas.add(magia);
	}

	public void afectadaPorMagia(Magia magia) {
		Unidad unidades[] = new Unidad[this.unidades.size()];
		unidades = this.unidades.toArray(unidades);
		
		for(Unidad unidad : unidades) {
			magia.afectar(unidad);
		}
		
		this.magiasActivas.remove(magia);
	}
	
	public Controlable seleccionRelevante() {
		
		Controlable elementoSeleccionado = null;
		
		if(!this.getUnidades().isEmpty()) {
			elementoSeleccionado = this.getUnidades().iterator().next();
		} else if(!this.getConstrucciones().isEmpty()) {
			elementoSeleccionado = this.getConstrucciones().iterator().next();
		} else if(this.getBase() != null) {
			elementoSeleccionado = this.getBase();
		}
		
		return elementoSeleccionado;
	}
	
}
