package supuestosTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.jugadores.JugadorTerran;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JuegoTester {

	@Before 
	public void inicioJuegoCorrectamente() throws ColorInvalido, NombreInvalido, FaltanJugadores {
		
		Juego.reiniciar();
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testElJugadorEnIniciarSeraElPrimeroEnSerAgregado() 
			throws NombreInvalido, ColorInvalido, FaltanJugadores, IOException {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.blue));
		juego.crearJugador(new JugadorTerran("jugadorProtoss", Color.red));
		
		juego.iniciarJuego("mapas/test.map");
		
		assertTrue(juego.turnoDe().getNombre().equals("jugadorTerran"));
		
	}
	
}
