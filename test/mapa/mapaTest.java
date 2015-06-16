package mapa;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;
import juego.razas.unidades.terran.Marine;

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
	public void testMapaDevuelveUnidadesCorrectamenteDentroDeUnRadio() {
		this.reiniciarJuego();
		
		Coordenada ubicacionMarine = new Coordenada(5,20);
		Coordenada ubicacionAltoTemplario = new Coordenada(5,21);
		
		Marine marine = new Marine();
		
		AltoTemplario altoTemplario = new AltoTemplario();
		
		marine.ubicar(ubicacionMarine);
		altoTemplario.ubicar(ubicacionAltoTemplario);
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		ArrayList<Unidad> unidades = mapa.unidadesACiertaDistanciaDe(ubicacionMarine, 1);
		
		assertEquals(unidades.size(), 2);
	}

}
