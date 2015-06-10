package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.Hospedable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Militable;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.NoEstaOcupadoPorUnidad;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.razas.unidades.Unidad;
import juego.recursos.Recurso;

public class Mapa {
	
	private HashMap<Coordenada,Celda> celdas;
	
	public Mapa() {
		this.celdas = new HashMap<Coordenada, Celda>();
	}
	
	public void agregarCelda(Coordenada coord, Celda celda) {		
		this.celdas.put(coord, celda);
	}	
	
	public void moverControlable(Controlable controlable, Coordenada coordFinal) 
			throws CeldaOcupada, CoordenadaFueraDeRango, ConstruccionesNoSeMueven, PropietarioInvalido {
		// Si se le indica al controlable que se mueve no importa si es volador o de tierra
		// ya que el controlable sabe como debe moverse.
		// Debe conocer su posicion o preguntarle al mapa su posicion actual.
		controlable.moverse(coordFinal);
	}
	
	public Recurso getRecurso(Coordenada coordenada) throws CoordenadaFueraDeRango {
		Celda celda = this.obtenerCelda(coordenada);
		return celda.obtenerRecurso();
	}	

	public Celda obtenerCelda(Coordenada coord) throws CoordenadaFueraDeRango {
		
		if (celdas.containsKey(coord)) {
			return celdas.get(coord); 
		} else {
			throw new CoordenadaFueraDeRango();
		}
	}
	
	public int distanciaEntreCeldas(Celda celdaInicial, Celda celdaFinal) {
		
		Coordenada posicionIncial = celdaInicial.obtenerPosicion();
		Coordenada posicionFinal = celdaFinal.obtenerPosicion();
		
		int distanciaX = Math.abs(posicionIncial.getX() - posicionFinal.getX());
		int distanciaY = Math.abs(posicionIncial.getY() - posicionFinal.getY());
		
		return distanciaX + distanciaY;
	}
	
	public Collection<Celda> obtenerRangoDeCeldas(Coordenada coordenadaDeterminante, int rangoX, int rangoY) 
			throws CoordenadaFueraDeRango, CeldaOcupada {
			
		
		Collection<Celda> rangoDeCeldas = new ArrayList<Celda>();
			
		int x = coordenadaDeterminante.getX();
		int y = coordenadaDeterminante.getY();
		
		for (int i = 0; i < (rangoY / rangoX); i++) {
			for (int j = 0; j < rangoX; j++) {
				rangoDeCeldas.add(this.obtenerCelda(new Coordenada(x+j, y+i)));
				rangoDeCeldas.add(this.obtenerCelda(new Coordenada(x+j, y+i)));
			}
		}
				
		Iterator<Celda> it = rangoDeCeldas.iterator();
		while (it.hasNext()) {
			Celda celda = it.next();
			if ((celda.ocupadoEnTierra()) || (celda.poseeRecursos())) throw new CeldaOcupada();
		}
			
		return rangoDeCeldas;
			
	}
	
	public Collection<Recolector> getRecolectores(Jugador jugador) {
		
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		Collection<Recolector> recolectores = new ArrayList<Recolector>();

		for (Celda celda : this.celdas.values()) { 
			
			// Un recolector es el unico que puede ocupar una celda con recursos y
			// ademas es el unico que ocupa una sola celda
			if ((celda.ocupadoEnTierra()) && (celda.poseeRecursos())) { 				
				Construible recolector = celda.obtenerConstruible();
				
				if ((((Controlable)recolector).esPropietario(jugadorActual)) && (recolector.construccionFinalizada()))	
					recolectores.add((Recolector)recolector);
			}
			
		}
		
		return recolectores;

	}

	public Collection<Hospedable> getHospedables(Jugador jugador) {
		
		Collection<Hospedable> hospedables = new ArrayList<Hospedable>();
		
		for (Celda celda : this.celdas.values()) { 
			
			if (celda.poseeConstruible()){
				Construible construccion = celda.obtenerConstruible();
				
				if ((construccion.construccionFinalizada()) && (construccion.puedeAlmacenarUnidades()) 
						&& (((Controlable)construccion)).esPropietario(jugador)) {
					
					if (!hospedables.contains(construccion)) hospedables.add((Hospedable)construccion);
					
				}
			}
			
		}
		
		return hospedables;
		
	}

	public Collection<Militable> getConstruccionesMilitares(Jugador jugador) {
	
		Collection<Militable> militables = new ArrayList<Militable>();
		
		for (Celda celda : this.celdas.values()) { 
			
			if (celda.poseeConstruible()) {
				Construible construccion = celda.obtenerConstruible();
				if ((construccion.construccionFinalizada()) && (((Controlable)construccion).esPropietario(jugador)) 
						&& (construccion.puedeCrearUnidades())) {
					if (!militables.contains(celda.obtenerConstruible())) {
						militables.add((Militable)celda.obtenerConstruible());
					}
				}
			}
			
		}
		
		return militables;
	
	}

	public Collection<Unidad> getUnidades(Jugador jugador) {
		
		Collection<Unidad> unidades = new ArrayList<Unidad>();
		
		for (Celda celda : this.celdas.values()) {
			
			if (celda.poseeUnidadTerrestre()) {
				
				try {
					Controlable unidad = (Controlable) celda.obtenerUnidadTerrestre();
					if ((unidad.esPropietario(jugador)) && (!unidades.contains(unidad))) {
						unidades.add((Unidad)unidad);
					}
				} catch (NoEstaOcupadoPorUnidad neopu) {}
				
			} else if (celda.poseeUnidadVoladora()) {
				
				try {
					Controlable unidad = (Controlable) celda.obtenerUnidadTerrestre();
					if ((unidad.esPropietario(jugador)) && (!unidades.contains(unidad))) {
						unidades.add((Unidad)unidad);
					}
				} catch (NoEstaOcupadoPorUnidad neopu) {}
				
			}
		}
		
		return unidades;
		
	}
	
}
