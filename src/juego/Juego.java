package juego;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.jugadores.Jugador;
import juego.mapa.GeneradorMapa;
import juego.mapa.Mapa;

public class Juego {

	private static Juego instance = new Juego();
	private Jugador turnoDe;
	private Collection<Jugador> listaDeJugadores;
	private Mapa mapa;
	
	private Juego(){
		
		super();
		this.listaDeJugadores = new ArrayList<Jugador>();
		this.turnoDe = null;
		this.mapa = null;
		
	}
	
	public static Juego getInstance() {
		return instance;
	}
	
	public void reiniciar() {
		instance = new Juego();
	}
		
	public Mapa getMapa() { return this.mapa; }
	
	public Jugador turnoDe() { return this.turnoDe; }
	
	public void iniciarJuego(String ubicacionDelMapa) throws FaltanJugadores, IOException {
		
		if (this.listaDeJugadores.size() < 2) throw new FaltanJugadores();
		
		GeneradorMapa generadorMapa = new GeneradorMapa();
		this.mapa = generadorMapa.obtenerMapa(ubicacionDelMapa);
		
	}
	
	public void crearJugador(Jugador jugadorNuevo) throws ColorInvalido, NombreInvalido {
		
		if (this.listaDeJugadores.isEmpty()) {
			
			this.turnoDe = jugadorNuevo;
			this.listaDeJugadores.add(turnoDe);
			
		} else {
		
			Iterator<Jugador> it  = this.listaDeJugadores.iterator();
		
			while (it.hasNext()) {
				Jugador jugadorActual = it.next();
				if (jugadorActual.getColor().equals(jugadorNuevo.getColor())) throw new ColorInvalido();
				if (jugadorActual.getNombre().equals(jugadorNuevo.getNombre())) throw new NombreInvalido();
			}
		
			this.listaDeJugadores.add(jugadorNuevo);
		
		}
			
	}

	public void finalizarTurno() {
		
		Iterator<Jugador> it = this.listaDeJugadores.iterator();

		while (it.hasNext()) {
			if (this.turnoDe.equals(it.next())) {
				try {
					this.turnoDe = it.next();
				} catch (NoSuchElementException nsee) {
					it = this.listaDeJugadores.iterator();
					this.turnoDe = it.next();
				}
			}
		}
		
		it = this.listaDeJugadores.iterator();
		
		while (it.hasNext()) {
			it.next().actualizarObservadores();
		}
		
	}
	
}
