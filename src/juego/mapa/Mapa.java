package juego.mapa;

import java.util.Collection;
import java.util.HashMap;

import juego.Juego;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.jugadores.Jugador;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.recursos.Mineral;

public class Mapa {
	
	private HashMap<Coordenada,Celda> celdas;
	
	public Mapa() {
		this.celdas = new HashMap<Coordenada, Celda>();
	}
	
	public void agregarCelda(Coordenada coord, Celda celda) {		
		this.celdas.put(coord, celda);
	}
	
	public void agregarControlable(Coordenada c, Controlable controlable) throws CeldaOcupada, CoordenadaFueraDeRango {
		
	/* Este metodo sirve para agregar un controlable en caso de que no exista en el mapa si no 
	 * debe indicarsele al controlable que se mueva
	 */
		Celda celda = this.obtenerCelda(c);
		controlable.ocuparCelda(celda);		
	}
	
	
	public boolean existeNodoDeMinerales(Coordenada coordenada) {
		
		//Debe responder si en esa coordenada existe un nodoDeMinerales
		return true;
	}
	
	public Mineral getNodoDeMinerales(Coordenada coordenada) {
		
		/* 
		 * Deberia devolver un Recurso generico, y despues con Double Dispatch
		 * verificar si podemos agregar el edificio correspondiente
		 */
		
		//Deberia buscar el nodo y devolverlo, por ahora solo devuelve un nodo cualquiera
		return (new Mineral(1000));
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
}
