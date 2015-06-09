package juego.interfaces.commandConstructor.habitables;

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
import juego.razas.terran.construcciones.DepositoSuministro;

public class ConstructorPilon extends CommandConstructor {

	private int costoMinerales = 100;
	
	@Override
	public void ejecutar(Protoss raza, Coordenada coordenada) 
			throws RecursosInsuficientes, CoordenadaFueraDeRango, CeldaOcupada {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		
		Collection<Celda> celdas = mapa.obtenerRangoDeCeldas(coordenada, 2, 2);
		
		jugador.consumirMinerales(this.costoMinerales);
		
		DepositoSuministro depositoSuministro = new DepositoSuministro();
		
		jugador.agregarConstructor(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(depositoSuministro);
		}
		
		this.enConstruccion = depositoSuministro;
		
	}
	
}