package juego.mapa;

import java.util.Collection;

import mapa.Coordenada;
import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Recolector;
import juego.jugadores.Jugador;
import juego.recursos.Mineral;

public class Mapa {
	
	//private Collection<Coordenada, Celda> mapa; 
	
	public Mapa() {

	}

	public boolean ubicacionOcupada(Coordenada coordenada) {	
		// Falta implementar, deberia decir si la ubicacion esta ocupada en esa coordenada 
		// tanto por enemigos como por aliados
		return false; 
	}

	public boolean existeNodoDeMinerales(Coordenada coordenada) {
		
		//Debe responder si en esa coordenada existe un nodoDeMinerales
		return true;
		
	}

	public Mineral getNodoDeMinerales(Coordenada coordenada) {
		
		//Deberia buscar el nodo y devolverlo, por ahora solo devuelve un nodo cualquiera
		return (new Mineral(1000));
		
	}

	public void enConstruccion(Construible construccion, Coordenada coordenada) {
		
		//Deberia ubicar la construccion en una celda y sus alrededores a partir de las coordenadas
		
	}

	public Collection<Recolector> getRecolectores() {
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		
		//Le pido al mapa los recolectores e itero sobre los recolectores pidiendoles recolectar
		//cada recolector agrega al contador especifico del material/gasvespeno del jugador asi
		//que no me tengo que preocupar por diferenciarlos, los trato a todos por igual
		return null;
	}

}
