package juego.interfaces.commandConstructor.almacenadores;

import java.util.ArrayList;
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
				
		Collection<Celda> celdas = this.obtenerRangoDeCeldas(coordenada);
		
		jugador.consumirMinerales(this.costoMinerales);
		
		DepositoSuministro depositoSuministro = new DepositoSuministro();
		
		jugador.agregarConstructor(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(depositoSuministro);
		}
		
		this.enConstruccion = depositoSuministro;
		
	}
	
	private Collection<Celda> obtenerRangoDeCeldas(Coordenada coordenadaDeterminante) 
			throws CoordenadaFueraDeRango, CeldaOcupada {
	
		Collection<Celda> rangoDeCeldas = new ArrayList<Celda>();
		Mapa mapa = Juego.getInstance().getMapa();
		
		int x = coordenadaDeterminante.getX();
		int y = coordenadaDeterminante.getY();
			
		rangoDeCeldas.add(mapa.obtenerCelda(coordenadaDeterminante)); x++;
		rangoDeCeldas.add(mapa.obtenerCelda(new Coordenada(x, y)));	
		
		Iterator<Celda> it = rangoDeCeldas.iterator();
		while (it.hasNext()) {
			Celda celda = it.next();
			if ((celda.ocupadoEnTierra()) || (celda.poseeRecursos())) throw new CeldaOcupada();
		}
		
		return rangoDeCeldas;
	
	}
	
}
