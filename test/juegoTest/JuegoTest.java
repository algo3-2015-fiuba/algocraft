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
import org.junit.Test;

public class JuegoTest {

	@Before 
	public void reset() {
		Juego.resetInstance();
	}
	
	@Test
	public void testJuegoInciaCorrectamenteEnCondicionesIndicadas() {
		Juego juego = Juego.getInstance();
		
		try {
				juego.crearJugador("jugadorTerran", new Terran(), Color.red);
				juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		} 
		catch (ColorInvalido ci) { assertTrue(false); }
		catch (NombreInvalido ni) { assertTrue(false); }
		
		try {
			
			juego.iniciarJuego();
			
		} catch (FaltanJugadores fj) { assertTrue(false); }
	}

}
