package juego.interfaces.commandConstructor.militares;

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
import juego.razas.Terran;
import juego.razas.terran.construcciones.Barraca;

public class ConstructorBarraca extends CommandConstructor {

	private int costoMinerales = 150;
	
	@Override
	public void ejecutar(Terran raza, Coordenada coordenada) 
			throws CoordenadaFueraDeRango, CeldaOcupada, RecursosInsuficientes {
		
		Juego juego = Juego.getInstance();
		Jugador jugador = juego.turnoDe();
				
		Collection<Celda> celdas = this.obtenerRangoDeCeldas(coordenada);
		
		jugador.consumirMinerales(this.costoMinerales);
		
		Barraca barraca = new Barraca();
		
		jugador.agregarConstructor(this);
		Iterator<Celda> it = celdas.iterator();
		while (it.hasNext()) {
			it.next().ocuparTierra(barraca);
		}
		
		this.enConstruccion = barraca;
		
	}
	
	private Collection<Celda> obtenerRangoDeCeldas(Coordenada coordenadaDeterminante) 
			throws CoordenadaFueraDeRango, CeldaOcupada {
		
		Mapa mapa = Juego.getInstance().getMapa();
		Collection<Celda> rangoDeCeldas = new ArrayList<Celda>();
		
		int x = coordenadaDeterminante.getX();
		int y = coordenadaDeterminante.getY();
			
		rangoDeCeldas.add(mapa.obtenerCelda(coordenadaDeterminante));
		rangoDeCeldas.add(mapa.obtenerCelda(new Coordenada(x+1, y)));
		rangoDeCeldas.add(mapa.obtenerCelda(new Coordenada(x, y+1)));
		rangoDeCeldas.add(mapa.obtenerCelda(new Coordenada(x+1, y+1)));
		
		Iterator<Celda> it = rangoDeCeldas.iterator();
		while (it.hasNext()) {
			Celda celda = it.next();
			if ((celda.ocupadoEnTierra()) || (celda.poseeRecursos())) throw new CeldaOcupada();
		}
		
		return rangoDeCeldas;
		
	}
	
}
