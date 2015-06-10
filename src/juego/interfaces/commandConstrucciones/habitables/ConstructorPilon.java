package juego.interfaces.commandConstrucciones.habitables;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.interfaces.CommandConstrucciones;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.terran.construcciones.DepositoSuministro;

public class ConstructorPilon extends CommandConstrucciones {

	private int costoMinerales;
	
	public ConstructorPilon() {
		super();
		this.costoMinerales = 100;
	}
	
	@Override
	public void iniciarConstruccion(Protoss raza, Coordenada coordenada) 
			throws RecursosInsuficientes, CoordenadaFueraDeRango, CeldaOcupada {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		
		Collection<Celda> celdas = mapa.obtenerRangoDeCeldas(coordenada, 2, 2);
		
		jugador.recursos().consumirMinerales(this.costoMinerales);
		
		DepositoSuministro depositoSuministro = new DepositoSuministro();
		
		jugador.observar(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(depositoSuministro);
		}
		
		this.enConstruccion = depositoSuministro;
		
	}
	
}
