package juego.informadores;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import juego.Juego;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.Construccion;
import juego.razas.unidades.Unidad;

public class VisionJugador {
	private Collection<Unidad> unidadesConVision;
	
	public VisionJugador(Collection<Unidad> unidadesJugador) {
		this.unidadesConVision = unidadesJugador;
	}
	
	public Set<Celda> celdasVisibles() {
		Set<Celda> celdasVisibles = new HashSet<Celda>();
		
		Iterator<Unidad> it = unidadesConVision.iterator();
		
		while(it.hasNext()) {
			this.agregarVisionDe(it.next(), celdasVisibles);
		}
		
		return celdasVisibles;
	}
	
	public boolean tieneVisionDe(Celda celdaEnCuestion) {
		return (this.celdasVisibles().contains(celdaEnCuestion));
	}
	
	public boolean tieneVisionDe(Unidad unidadEnCuestion) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Celda celdaDeUnidad = null;
		try {
			celdaDeUnidad = mapa.obtenerCelda(unidadEnCuestion.coordenadas());
		} catch (CoordenadaFueraDeRango cfdr) { }
		
		return this.tieneVisionDe(celdaDeUnidad);
	}
	
	private void agregarVisionDe(Unidad visor, Set<Celda> celdasVisibles) {
		Mapa mapa = Juego.getInstance().getMapa();
		
		Coordenada posicionVisor = visor.coordenadas();
		
		Collection<Celda> celdasEnVision = mapa.obtenerRadialmenteRangoDeCeldasDisponibles(posicionVisor, visor.vision());
		
		Iterator<Celda> it = celdasEnVision.iterator();
		
		while(it.hasNext()) {
			Celda celdaVisible = it.next();
			celdasVisibles.add(celdaVisible);
		}
	}
	
	public void agregarVisionDe(Construccion visor) {
		//TODO: que las construcciones den vision
	}
}
