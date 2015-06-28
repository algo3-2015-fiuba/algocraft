package mapaTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Collection;

import juego.Juego;
import juego.excepciones.InicioInvalido;
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
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
		} catch (InicioInvalido ii) {}
		
		try {
			juego.iniciarJuego("mapas/test.map");
		} catch (InicioInvalido ii) {}
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSeleccionaRadialmenteCeldasCorrectamente() {
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Coordenada centro = new Coordenada(5, 5);
		
		Collection<Celda> celdasSeleccionadas = mapa.obtenerRangoRadialDeCeldas(centro, 1);
		
		/*    
		 *    ----O----
		 *    ---OOO---
		 *    ----O----
		 */
		assertEquals(celdasSeleccionadas.size(), 5);
		
		Collection<Celda> celdasSeleccionadas2 = mapa.obtenerRangoRadialDeCeldas(centro, 2);
		
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
