package juego.interfaces.commandConstrucciones.militares;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.CommandConstrucciones;
import juego.interfaces.Construible;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.Terran;

public class ConstructorPuertoEstelar extends CommandConstrucciones {

	private int costoMinerales;
	
	public ConstructorPuertoEstelar() {
		super();
		this.costoMinerales = 150;		
	}
	
	@Override
	public void iniciarConstruccion(Terran raza, Coordenada coordenada) 
			throws CoordenadaFueraDeRango, CeldaOcupada, RecursosInsuficientes {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		int costoGasVespeno = 100;
		
		Collection<Celda> celdas = mapa.obtenerRangoDeCeldas(coordenada, 2, 6);
		
		jugador.consumirMinerales(this.costoMinerales);
		jugador.consumirGasVespeno(costoGasVespeno);
		
		Construible puertoEstelar = raza.obtenerPuertoEstelar();
		
		jugador.agregarConstructor(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(puertoEstelar);
		}
		
		this.enConstruccion = puertoEstelar;
		
	}
	
	@Override
	public void iniciarConstruccion(Protoss raza, Coordenada coordenada) 
			throws CoordenadaFueraDeRango, CeldaOcupada, RecursosInsuficientes {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		int costoGasVespeno = 150;
		
		Collection<Celda> celdas = mapa.obtenerRangoDeCeldas(coordenada, 2, 6);
		
		jugador.consumirMinerales(this.costoMinerales);
		jugador.consumirGasVespeno(costoGasVespeno);
		
		Construible puertoEstelar = raza.obtenerPuertoEstelar();
		
		jugador.agregarConstructor(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(puertoEstelar);
		}
		
		this.enConstruccion = puertoEstelar;
		
	}
	
}
