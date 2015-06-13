package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import juego.interfaces.Construible;
import juego.interfaces.Terrestre;
import juego.interfaces.Volador;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.materiales.Material;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.razas.unidades.Unidad;
import juego.recursos.Recurso;

public class Celda {
	
	private Material material;
	private Recurso recurso;
	private Collection<Construible> construibles;
	private Collection<Unidad> unidades;
	
	public Celda(Material material, Recurso recurso, Coordenada posicion) {
		this.material = material;
		this.recurso = recurso;
		this.construibles = new ArrayList<Construible>();
		this.unidades = new ArrayList<Unidad>();
		
	}
	public boolean poseeRecursos() { return (this.recurso != null); }
	public boolean poseeConstruible() { return (!this.construibles.isEmpty()); }
	public Material getMaterial() { return (this.material); }	
	public Recurso getRecurso() { return (this.recurso); }
	
	public void ocupar(Terrestre terrestre) throws UbicacionInvalida {
		
		if (!this.material.equals(Material.tierra)) throw new UbicacionInvalida();
		
		if (!this.construibles.isEmpty()) {
			throw new CeldaOcupada();
		}
		
		if (!this.unidades.isEmpty()) {
			
			Iterator<Unidad> it = this.unidades.iterator();
			while (it.hasNext()) {
				
				if (it.next().ocupanMismoEspacio(terrestre)) {
					throw new CeldaOcupada();
				}
				
			}
			
		}
		
		this.unidades.add((Unidad)terrestre);
		
	}
	
	public void ocupar(Volador volador) throws CeldaOcupada {
		
		if (!this.unidades.isEmpty()) {
			
			Iterator<Unidad> it = this.unidades.iterator();
			while (it.hasNext()) {
				
				if (it.next().ocupanMismoEspacio(volador)) {
					throw new CeldaOcupada();
				}
				
			}
			
		}
		
		this.unidades.add((Unidad)volador);	
		
	}
	
	public void ocupar(Construible construible) throws CeldaOcupada {
		
		if ((this.construibles.isEmpty()) && (this.material.equals(Material.tierra))) {
			this.construibles.add(construible);
		} else {
			throw new CeldaOcupada();
		}
		
	}
	
	public boolean esPosibleConstruir(ConstruccionRecolectora recolector) {
		
		if (!this.construibles.isEmpty()) return false;
		
		if (this.recurso == null) return false;
		
		return (this.recurso.puedeRecolectar(recolector));
		
	}

	public boolean esPosibleConstruir(Construible construible) {
		
		if (!this.construibles.isEmpty()) return false;
		
		if (this.recurso != null) return false;
	
		Iterator<Unidad> it = this.unidades.iterator();
		
		while (it.hasNext()) {
			if (it.next().ocupanMismoEspacio(construible)) return false;
		}
		
		return true;
	
	}
	
}
