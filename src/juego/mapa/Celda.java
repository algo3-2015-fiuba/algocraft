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
import juego.razas.construcciones.Construccion;
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
	public Material getMaterial() { return (this.material); }	
	public Recurso getRecurso() { return (this.recurso); }
	
	public void ocuparTerrestre(Unidad terrestre) throws UbicacionInvalida {
		
		if (!this.material.equals(Material.tierra)) throw new UbicacionInvalida();
		if (this.poseeRecursos()) throw new UbicacionInvalida();
		
		if (!this.construibles.isEmpty()) {
			throw new CeldaOcupada();
		}
		
		Iterator<Unidad> it = this.unidades.iterator();
		while (it.hasNext()) {
				
			if (it.next().ocupanMismoEspacio(terrestre)) {
				throw new CeldaOcupada();
			}
				
		}
		
		this.unidades.add((Unidad)terrestre);
		
	}
	
	public void ocuparVolador(Unidad volador) throws UbicacionInvalida {
		
		Iterator<Unidad> it = this.unidades.iterator();
		while (it.hasNext()) {
				
			if (it.next().ocupanMismoEspacio(volador)) {
				throw new CeldaOcupada();
			}
				
		}
			
		this.unidades.add((Unidad)volador);	
		
	}
	
	public void ocuparConstruccion(Construccion construible) throws UbicacionInvalida {
		
		if (!this.material.equals(Material.tierra)) throw new UbicacionInvalida();

		Iterator<Unidad> itU = this.unidades.iterator();
		while (itU.hasNext()) {
				
			if (itU.next().ocupanMismoEspacio(construible)) {
				throw new CeldaOcupada();
			}
				
		}
		
		if (this.construibles.isEmpty()) {
			this.construibles.add(construible);
		} else {
			throw new CeldaOcupada();
		}
		
	}
	
	public void desocupar(Unidad unidad) {
		this.unidades.remove(unidad);
	}
	
	public void desocupar(Construible construible) {
		this.construibles.remove(construible);
	}
	
}
