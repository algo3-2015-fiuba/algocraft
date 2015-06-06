package juegoTest;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.razas.Protoss;
import juego.razas.Terran;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JuegoTest {

	@Before 
	public void inicioJuegoCorrectamente() throws ColorInvalido, NombreInvalido, FaltanJugadores {
		
		Juego juego = Juego.getInstance();
		juego.reiniciar();
		juego = Juego.getInstance();
		
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		
		juego.iniciarJuego();
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testJuegoNoPuedeTenerDosJugadoresConElMismoNombre() throws NombreInvalido, ColorInvalido {
		
		Juego juego = Juego.getInstance();
		
		exception.expect(NombreInvalido.class);
		juego.crearJugador("jugadorTerran", new Terran(), Color.black);

	}

	@Test
	public void testJuegoNoPuedeTenerDosJugadoresConElMismoColor() throws ColorInvalido, NombreInvalido {
		Juego juego = Juego.getInstance();
		
		exception.expect(ColorInvalido.class);
		juego.crearJugador("jugadorTerran2", new Terran(), Color.red);
	
	}

	@Test
	public void testJuegoNoPuedeEmpezarSinJugadores() throws FaltanJugadores, ColorInvalido, NombreInvalido {
		Juego juego = Juego.getInstance();
		juego.reiniciar();
		juego = Juego.getInstance();
		
		exception.expect(FaltanJugadores.class);
		juego.iniciarJuego();	
		
		this.inicioJuegoCorrectamente();
		
	}
	
	@Test 
	public void testJuegoNoPuedeEmpezarConMenosDeDosJugadores() throws FaltanJugadores, ColorInvalido, NombreInvalido {
		
		Juego juego = Juego.getInstance();
		juego.reiniciar();
		juego = Juego.getInstance();
		
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		
		exception.expect(FaltanJugadores.class);
		juego.iniciarJuego();
		
		this.inicioJuegoCorrectamente();
		
	}

}
