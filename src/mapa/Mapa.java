package mapa;

import java.util.Collection;
import java.util.HashMap;

import mapa.Material.Materiales;
import mapa.excepciones.CeldaNoVacia;
import mapa.excepciones.CoordenadaFueraDeBordes;
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
	
	public void agregarCelda(Coordenada coord, Materiales material, Recurso recurso) {		
		Celda celda = new Celda(material, recurso);
		this.celdas.put(coord, celda);
	}
	
	public void agregarControlable(Coordenada c, Controlable controlable) throws CeldaNoVacia, CoordenadaFueraDeBordes {
		Celda celda = this.obtenerCelda(c);
		
		controlable.ubicarseEnCelda(celda);
		
		this.cantidadDeControlablees++;
	}
	
	public void asignarBordes(int xMaximo, int yMaximo) {
		this.xMaximo = xMaximo;
		this.yMaximo = yMaximo;
	}
	
	public void enConstruccion(Construible construccion, Coordenada coordenada) {
		
		//Deberia ubicar la construccion en una celda y sus alrededores a partir de las coordenadas
		
	}
	
	public boolean existeNodoDeMinerales(Coordenada coordenada) {
		
		//Debe responder si en esa coordenada existe un nodoDeMinerales
		return true;
	}
	
	public Mineral getNodoDeMinerales(Coordenada coordenada) {
		
		/* 
		 * Debería devolver un Recurso generico, y despues con Double Dispatch
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
	
	public void moverControlableEnAire(Coordenada c1, Coordenada c2) throws CeldaNoVacia, CoordenadaFueraDeBordes {
		Controlable original = this.obtenerCelda(c1).obtenerControlableEnAire();
		
		this.obtenerCelda(c1);
		
		this.agregarControlable(c2, original);
	}
	
	public void moverControlableEnTierra(Coordenada c1, Coordenada c2) throws CeldaNoVacia, CoordenadaFueraDeBordes {
		Controlable original = this.obtenerCelda(c1).obtenerControlableEnTierra();
		
		this.obtenerCelda(c1).removerControlableEnTierra();
		
		this.agregarControlable(c2, original);
	}

	public Celda obtenerCelda(Coordenada c) throws CoordenadaFueraDeBordes {
		if(c.getX() > this.xMaximo || c.getY() > this.yMaximo) {
			throw new CoordenadaFueraDeBordes();
		}
		else {
			return celdas.get(c);
		}
	}

	public boolean ubicacionOcupada(Coordenada coordenada) {
		
		/*
		 * Deberia acceder directamente a la celda en vez de usar este método
		 * Sino llenamos a Mapa con copias de métodos de Celda
		 */
		
		// Falta implementar, deberia decir si la ubicacion esta ocupada en esa coordenada 
		// tanto por enemigos como por aliados
		return false; 
	}

}
