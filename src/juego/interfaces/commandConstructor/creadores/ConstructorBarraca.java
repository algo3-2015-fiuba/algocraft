package juego.interfaces.commandConstructor.creadores;

import java.util.ArrayList;
import java.util.Collection;

import juego.Juego;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.protoss.construcciones.Asimilador;
import juego.razas.terran.construcciones.Barraca;
import juego.recursos.Recurso;

public class ConstructorBarraca extends CommandConstructor {
	
	private int costoMinerales = 150;

	@Override
	public void ejecutar(Protoss raza, Coordenada coordIncial, Coordenada coordFinal) 
			throws RecursosInsuficientes, UbicacionInvalida, CoordenadaFueraDeRango, CeldaOcupada {
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		
		ArrayList<Celda> celdasAConstruir = (ArrayList<Celda>) mapa.obtenerRangoDeCeldas(coordIncial, coordFinal);
		
		for (Celda celda : celdasAConstruir) { 
			if (celda.ocupadoEnTierra()) throw new CeldaOcupada();
		}
		
		jugador.consumirMinerales(this.costoMinerales);
		
		Barraca barraca = new Barraca();
		
		jugador.agregarConstructor(this);
		
		for (Celda celda : celdasAConstruir) { 
			celda.agregarControlable(barraca);
		}
		
		this.enConstruccion = barraca;
	}
	
}
