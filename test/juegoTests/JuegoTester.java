package juegoTests;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.InicioInvalido;
import juego.excepciones.NombreInvalido;
import juego.jugadores.JugadorTerran;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JuegoTester {

	@Before 
	public void inicioJuegoCorrectamente() throws InicioInvalido {
		
		Juego.reiniciar();
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testJuegoNoPuedeTenerDosJugadoresConElMismoNombre() throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.black));
		
		exception.expect(NombreInvalido.class);
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));

	}

	@Test
	public void testJuegoNoPuedeTenerDosJugadoresConElMismoColor() throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
		
		exception.expect(ColorInvalido.class);
		juego.crearJugador(new JugadorTerran("jugadorTerran2", Color.red));
	
	}

	@Test
	public void testJuegoNoPuedeEmpezarSinJugadores() throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		exception.expect(FaltanJugadores.class);
		juego.iniciarJuego("mapas/test.map");	
		
	}
	
	@Test 
	public void testJuegoNoPuedeEmpezarConMenosDeDosJugadores()	throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
		
		exception.expect(FaltanJugadores.class);
		juego.iniciarJuego("mapas/test.map");
		
	}

}
