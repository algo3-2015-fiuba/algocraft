package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.jugadores.Jugador;
import mapa.Mapa;
import juego.razas.Raza;

public class Juego {

	private static Juego instance = new Juego();
	private Jugador turnoDe;
	private Collection<Jugador> listaDeJugadores;
	private Mapa mapa;
	
	public static Juego getInstance() {
		return instance;
	}
	
	public void reiniciar() {
		instance = new Juego();
	}
	
	private Juego(){
		
		this.listaDeJugadores = new ArrayList<Jugador>();
		this.turnoDe = null;
		this.mapa = new Mapa();
		
	}
	
	public void crearJugador(String nombre, Raza raza, Color color) throws ColorInvalido, NombreInvalido {
		
		if (this.listaDeJugadores.isEmpty()) {
			
			this.turnoDe = new Jugador(nombre, raza, color);
			this.listaDeJugadores.add(turnoDe);
			
		} else {
		
			Iterator<Jugador> it  = this.listaDeJugadores.iterator();
		
			try {
				while (it.hasNext()) {
					Jugador jugadorActual = it.next();
					if (jugadorActual.esDeColor(color)) throw new ColorInvalido();
					if (jugadorActual.suNombreEs(nombre)) throw new NombreInvalido();
				}
			} finally {}
		
			this.listaDeJugadores.add(new Jugador(nombre, raza, color));
		
		}
			
	}

	public Jugador turnoDe() { return this.turnoDe; }

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
		
	}

	public void iniciarJuego() throws FaltanJugadores {
		
		if (this.listaDeJugadores.size() <= 1) throw new FaltanJugadores();
		
		//Faltaria inicializar el mapa, usar el generador del mismo
		
	}

	public Mapa getMapa() { return this.mapa; }
	
}
