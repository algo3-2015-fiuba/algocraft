package juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.InicioInvalido;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.GeneradorMapa;
import juego.mapa.Mapa;

public class Juego {

	private static Juego instance = new Juego();
	private Jugador turnoDe;
	private Collection<Jugador> jugadores;
	private Mapa mapa;
	
	private Juego() {
		
		super();
		this.jugadores = new ArrayList<Jugador>();
		this.turnoDe = null;
		this.mapa = null;
		
	}
	
	public static Juego getInstance() {
		return instance;
	}
	
	public static void reiniciar() {
		instance = new Juego();
	}
	
	public Jugador ganador() {
		
		if (this.finalizo()) {
			
			Iterator<Jugador> it = this.jugadores.iterator();
			if (it.hasNext()) return it.next();
			else return null;
			
		} else {
			
			return null;
		}
		
	}
	
	public boolean finalizo() {
		
		Collection<Jugador> perdedores = new ArrayList<Jugador>();
		
		Iterator<Jugador> it = this.jugadores.iterator();
		
		while (it.hasNext()) {
			
			Jugador actual = it.next();
			if (actual.perdio()) perdedores.add(actual);
			
		}
		
		this.jugadores.removeAll(perdedores);
		
		return (this.jugadores.size() <= 1);
		
	}
		
	public Mapa getMapa() { return this.mapa; }
	
	public Jugador turnoDe() { return this.turnoDe; }
	
	public void iniciarJuego(String ubicacionDelMapa) throws InicioInvalido {
		
		if (this.jugadores.size() < 2) throw new FaltanJugadores();
		
		GeneradorMapa generadorMapa = new GeneradorMapa();
		this.mapa = generadorMapa.obtenerMapa(ubicacionDelMapa, this.jugadores);
		
		try {
			
			Iterator<Jugador> it = this.jugadores.iterator();
			while (it.hasNext()) {
				it.next().inicializarMapa();
			}
		
		} catch (UbicacionInvalida ui) { 
			throw new InicioInvalido(); 
		}
		
	}
	
	public void crearJugador(Jugador jugadorNuevo) throws InicioInvalido {
		
		if (this.jugadores.isEmpty()) {
			
			this.turnoDe = jugadorNuevo;
			this.jugadores.add(turnoDe);
			
		} else {
		
			Iterator<Jugador> it  = this.jugadores.iterator();
		
			while (it.hasNext()) {
				Jugador jugadorActual = it.next();
				if (jugadorActual.getColor().equals(jugadorNuevo.getColor())) throw new ColorInvalido();
				if (jugadorActual.getNombre().equals(jugadorNuevo.getNombre())) throw new NombreInvalido();
			}
		
			this.jugadores.add(jugadorNuevo);
		
		}
			
	}

	public void finalizarTurno() {
		
		if (!this.finalizo()) {
		
			Iterator<Jugador> it = this.jugadores.iterator();
	
			while (it.hasNext()) {
				if (this.turnoDe.equals(it.next())) {
					try {
						this.turnoDe = it.next();
					} catch (NoSuchElementException nsee) {
						it = this.jugadores.iterator();
						this.turnoDe = it.next();
					}
				}
			}
			
			it = this.jugadores.iterator();
			
			while (it.hasNext()) {
				it.next().actualizarObservadores();
			}
		
		}
		
	}
	
}
