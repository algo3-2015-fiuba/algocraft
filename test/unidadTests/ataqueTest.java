package unidadTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.YaFueDestruido;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.Terran;
import juego.razas.terran.unidades.Marine;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ataqueTest {
	
	@Before 
	public void reiniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		
		juego.iniciarJuego("mapas/test.map");
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testUnMarineAtacaAOtroYPierdeSuVidaCorrespondiente() throws CeldaOcupada, CoordenadaFueraDeRango, ColorInvalido, NombreInvalido, FaltanJugadores, IOException, YaFueDestruido {
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		
		Jugador jugadorTerran = juego.turnoDe();
		
		Coordenada coordMarine1 = new Coordenada(2,1);
		Coordenada coordMarine2 = new Coordenada(3,1);
		
		Celda celdaMarine1 = mapa.obtenerCelda(coordMarine1);
		Marine marine1 = new Marine(jugadorTerran);
		marine1.ocuparCelda(celdaMarine1);
		
		Celda celdaMarine2 = mapa.obtenerCelda(coordMarine2);
		Marine marine2 = new Marine(jugadorTerran);
		marine2.ocuparCelda(celdaMarine2);
		
		/*
		 * Un marine tiene 40 de vida y ataca 6 a otros marines.
		 * 
		 */
		
		marine1.atacar(marine2);
		
		assertTrue(Math.abs(marine2.vidaRestante() - 34.0) < 0.00000001);
	}
	
	@Test
	public void testUnMarineAtacaAMuchoAOtroYMuere() throws CeldaOcupada, CoordenadaFueraDeRango, ColorInvalido, NombreInvalido, FaltanJugadores, IOException, YaFueDestruido {
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		
		Jugador jugadorTerran = juego.turnoDe();
		
		Coordenada coordMarine1 = new Coordenada(2,1);
		Coordenada coordMarine2 = new Coordenada(3,1);
		
		Celda celdaMarine1 = mapa.obtenerCelda(coordMarine1);
		Marine marine1 = new Marine(jugadorTerran);
		marine1.ocuparCelda(celdaMarine1);
		
		Celda celdaMarine2 = mapa.obtenerCelda(coordMarine2);
		Marine marine2 = new Marine(jugadorTerran);
		marine2.ocuparCelda(celdaMarine2);
		
		/*
		 * Un marine tiene 40 de vida y ataca 6 a otros marines.
		 * 
		 */
		
		for(int i = 0; i < 7; i++) {
			marine1.atacar(marine2);
		}
		
		assertTrue(marine2.estaMuerto());
	}
	
	@Test
	public void testUnMarineQueNoAtacaLoSuficienteAOtroNoMuere() throws CeldaOcupada, CoordenadaFueraDeRango, ColorInvalido, NombreInvalido, FaltanJugadores, IOException, YaFueDestruido {
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		
		Jugador jugadorTerran = juego.turnoDe();
		
		Coordenada coordMarine1 = new Coordenada(2,1);
		Coordenada coordMarine2 = new Coordenada(3,1);
		
		Celda celdaMarine1 = mapa.obtenerCelda(coordMarine1);
		Marine marine1 = new Marine(jugadorTerran);
		marine1.ocuparCelda(celdaMarine1);
		
		Celda celdaMarine2 = mapa.obtenerCelda(coordMarine2);
		Marine marine2 = new Marine(jugadorTerran);
		marine2.ocuparCelda(celdaMarine2);
		
		/*
		 * Un marine tiene 40 de vida y ataca 6 a otros marines.
		 * 
		 */
		
		for(int i = 0; i < 4; i++) {
			marine1.atacar(marine2);
		}
		
		assertFalse(marine2.estaMuerto());
	}

}
