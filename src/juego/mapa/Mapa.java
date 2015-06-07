package juego.mapa;

import java.util.Collection;
import java.util.HashMap;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.jugadores.Jugador;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.recursos.Recurso;

public class Mapa {
	
	private HashMap<Coordenada,Celda> celdas;
	
	public Mapa() {
		this.celdas = new HashMap<Coordenada, Celda>();
	}
	
	public void agregarCelda(Coordenada coord, Celda celda) {		
		this.celdas.put(coord, celda);
	}	
	
	public boolean existeNodoDeMinerales(Coordenada coordenada) {
		
		//Debe responder si en esa coordenada existe un nodoDeMinerales
		return true;
	}
	
	public Collection<Recolector> getRecolectores() {
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		
		//Le pido al mapa los recolectores e itero sobre los recolectores pidiendoles recolectar
		//cada recolector agrega al contador especifico del material/gasvespeno del jugador asi
		//que no me tengo que preocupar por diferenciarlos, los trato a todos por igual
		return null;
	}
	
	public void moverControlable(Controlable controlable, Coordenada coordFinal) throws CeldaOcupada, CoordenadaFueraDeRango {
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

	public Recurso getRecurso(Coordenada coordenada) throws CoordenadaFueraDeRango {
		Celda celda = this.obtenerCelda(coordenada);
		return celda.obtenerRecurso();
	}
}
