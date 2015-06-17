package mapaTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Collection;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class mapaTest {
	
	@Before 
	public void reiniciarJuego() {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
		} catch (ColorInvalido ci) {
			assertTrue(false);
		} catch (NombreInvalido ni) {
			assertTrue(false);
		}
		
		try {
			juego.iniciarJuego("mapas/test.map");
		} catch (FaltanJugadores fj) {
			assertTrue(false);
		} catch (IOException ioe) {
			assertTrue(false);
		}
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSeleccionaRadialmenteCeldasCorrectamente() {
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Coordenada centro = new Coordenada(5, 5);
		
		Collection<Celda> celdasSeleccionadas = mapa.obtenerRadialmenteRangoDeCeldasDisponibles(centro, 1);
		
		/*    
		 *    ----O----
		 *    ---OOO---
		 *    ----O----
		 */
		assertEquals(celdasSeleccionadas.size(), 5);
		
		Collection<Celda> celdasSeleccionadas2 = mapa.obtenerRadialmenteRangoDeCeldasDisponibles(centro, 2);
		
		/*    
		 * 	  -----O----
		 *    ----OOO---
		 *    ---OOOOO---
		 *    ----OOO---
		 *    -----O----
		 */
		assertEquals(celdasSeleccionadas2.size(), 13);
		
	}
}
