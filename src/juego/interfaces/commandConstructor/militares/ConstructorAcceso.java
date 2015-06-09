package juego.interfaces.commandConstructor.militares;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.protoss.construcciones.Acceso;

public class ConstructorAcceso extends CommandConstructor {

	private int costoMinerales;
	
	public ConstructorAcceso() {
		super();
		this.costoMinerales = 150;
	}
	
	@Override
	public void ejecutar(Protoss raza, Coordenada coordenada) 
			throws CoordenadaFueraDeRango, CeldaOcupada, RecursosInsuficientes {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		
		Collection<Celda> celdas = mapa.obtenerRangoDeCeldas(coordenada, 2, 4);
		
		jugador.consumirMinerales(this.costoMinerales);
		
		Acceso acceso = new Acceso();
		
		jugador.agregarConstructor(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(acceso);
		}
		
		this.enConstruccion = acceso;
		
	}
	
}
