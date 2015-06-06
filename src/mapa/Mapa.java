package mapa;

import java.util.Collection;
import java.util.HashMap;

import mapa.Material.Materiales;
import mapa.excepciones.CeldaNoVacia;
import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Recolector;
import juego.jugadores.Jugador;
import juego.razas.interfaces.Controlable;
import juego.recursos.Mineral;

public class Mapa {
	
	private int xMaximo, yMaximo;
	private HashMap<Coordenada,Celda> celdas;
	private int cantidadDeControlablees;
	
	public Mapa() {
		this.celdas = new HashMap<Coordenada, Celda>();
	}
	
	public void asignarBordes(int xMaximo, int yMaximo) {
		this.xMaximo = xMaximo;
		this.yMaximo = yMaximo;
	}
	
	public Celda obtenerCelda(Coordenada c) {
		return celdas.get(c);
	}
	
	public Controlable obtenerControlable(Coordenada c) {
		return this.obtenerCelda(c).getControlable();
	}
	
	public void moverControlable(Coordenada c1, Coordenada c2) throws CeldaNoVacia {
		Controlable original = this.obtenerCelda(c1).getControlable();
		
		this.removerControlable(c1);
		
		this.agregarControlable(c2, original);
	}
	
	public void removerControlable(Coordenada c) {
		this.obtenerCelda(c).removerControlable();
		
		this.cantidadDeControlablees--;
	}
	
	public void agregarControlable(Coordenada c, Controlable Controlable) throws CeldaNoVacia {
		this.obtenerCelda(c).agregarControlable(Controlable);
		
		this.cantidadDeControlablees++;
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

	public void agregarCelda(Coordenada coord, Materiales material) {
		// TODO Auto-generated method stub
		
	}

}
