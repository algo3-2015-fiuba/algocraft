package ataqueTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ataqueSimpleTest {

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
	public void testSiUnMarineAtacaAOtroEsteNoMuerePrematuramente() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion {
		
		this.reiniciarJuego();
		
		Coordenada ubicacionMarine1 = new Coordenada(0,20);
		Coordenada ubicacionMarine2 = new Coordenada(0,21);
		
		Marine marine1 = new Marine();
		Marine marine2 = new Marine();
		
		marine1.ubicar(ubicacionMarine1);
		marine2.ubicar(ubicacionMarine2);
		
		marine1.atacar(marine2);
		
		assertFalse(marine2.estaMuerto()); // 34 de vida
		
	}
	
	@Test
	public void testSiUnMarineAtacaAOtroEsteMuereAlQuedarseSinVida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion {
		
		this.reiniciarJuego();
		
		Coordenada ubicacionMarine1 = new Coordenada(0,20);
		Coordenada ubicacionMarine2 = new Coordenada(0,21);
		
		Marine marine1 = new Marine();
		Marine marine2 = new Marine();
		
		marine1.ubicar(ubicacionMarine1);
		marine2.ubicar(ubicacionMarine2);
		
		for(int i = 0; i < 7; i++) {
			marine1.atacar(marine2);
		}
		
		assertTrue(marine2.estaMuerto()); // 0 de vida
	}
	

	

}
