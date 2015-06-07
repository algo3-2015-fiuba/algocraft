package juego.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.recursos.Recurso;

public class Mapa {
	
	private HashMap<Coordenada,Celda> celdas;
	
	public Mapa() {
		this.celdas = new HashMap<Coordenada, Celda>();
	}
	
	public void agregarCelda(Coordenada coord, Celda celda) {		
		this.celdas.put(coord, celda);
	}	
	
	public Collection<Recolector> getRecolectores() {
		
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		ArrayList<Recolector> recolectores = new ArrayList<Recolector>();

		for (Celda celda : this.celdas.values()) { 
			
			if ((celda.ocupadoEnTierra()) && (celda.poseeRecursos())) { 				
				Controlable recolector = celda.obtenerControlableEnTierra();
				
				if ((recolector.esPropietario(jugadorActual)) && (((Construible)recolector).construccionFinalizada()))	
					recolectores.add((Recolector)recolector);
			}
			
		}
		
		return recolectores;

	}
	
	public void moverControlable(Controlable controlable, Coordenada coordFinal) 
			throws CeldaOcupada, CoordenadaFueraDeRango, ConstruccionesNoSeMueven, PropietarioInvalido {
		//Si se le indica al controlable que se mueve no importa si es volador o de tierra
		//ya que el controlable sabe como debe moverse.
		//Debe conocer su posicion
		controlable.moverse(coordFinal);
	}
	

	public Celda obtenerCelda(Coordenada coord) throws CoordenadaFueraDeRango {
		
		if (celdas.containsKey(coord)) {
			return celdas.get(coord); 
		} else {
			throw new CoordenadaFueraDeRango();
		}
		
	}
	
	public Collection<Celda> obtenerRangoDeCeldas(Coordenada coordIncial, Coordenada coordFinal) throws CoordenadaFueraDeRango {
		ArrayList<Celda> celdas = new ArrayList<Celda>();
		
		celdas.add(this.obtenerCelda(coordIncial));
		
		for(int i = coordIncial.getX(); i < coordFinal.getX(); i++) {
			for(int j = coordIncial.getY(); j < coordFinal.getY(); j++) {
				celdas.add(this.obtenerCelda(new Coordenada(i, j)));
			}			
		}
		
		celdas.add(this.obtenerCelda(coordFinal));
		
		return celdas;
	}

	public Recurso getRecurso(Coordenada coordenada) throws CoordenadaFueraDeRango {
		Celda celda = this.obtenerCelda(coordenada);
		return celda.obtenerRecurso();
	}
}
