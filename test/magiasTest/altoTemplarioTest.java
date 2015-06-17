package magiasTest;

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
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.razas.unidades.protoss.AltoTemplario;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class altoTemplarioTest {

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
	public void testSiUnMarineEstaBajoUnaTormentaDosTurnosMuere() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion {
		
		this.reiniciarJuego();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarine = new Coordenada(0,20);
		Coordenada ubicacionAltoTemplario = new Coordenada(8,21);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarine);
		
		AltoTemplario altoTemplario = new AltoTemplario();
		altoTemplario.moverse(ubicacionAltoTemplario);
		
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorReceptor.asignarUnidad(marine);
		
		
		for (int i = 0; i < 5; i++) {
			Juego.getInstance().turnoDe().finalizarTurno();
		}
		
		altoTemplario.lanzarTormentaPsionica(ubicacionMarine);
		
		Celda celdaMarine = Juego.getInstance().getMapa().obtenerCelda(ubicacionMarine);
		assertTrue(celdaMarine.contiene(marine));
		
		
		/*
		 * Pasan dos turnos
		 */
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertTrue(celdaMarine.contiene(marine));
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertTrue(celdaMarine.contiene(marine));
		
		/*
		 * Y debería morir el Marine
		 */
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertFalse(celdaMarine.contiene(marine));
	}
	
	@Test
	public void testSiUnMarineEstaBajoUnaTormentaPeroSeMueveAntesNoMuere() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion {
		
		this.reiniciarJuego();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionTormenta = new Coordenada(0,20);
		Coordenada ubicacionMarineBajoTormenta = new Coordenada(5,20);
		Coordenada ubicacionMarineFueraDeTormenta = new Coordenada(6,20);
		Coordenada ubicacionAltoTemplario = new Coordenada(8,21);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarineBajoTormenta);
		
		AltoTemplario altoTemplario = new AltoTemplario();
		altoTemplario.moverse(ubicacionAltoTemplario);
		
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorReceptor.asignarUnidad(marine);
		
		/*
		 * Gana energia el Alto Templario...
		 */
		
		for (int i = 0; i < 5; i++) {
			Juego.getInstance().turnoDe().finalizarTurno();
		}
		
		altoTemplario.lanzarTormentaPsionica(ubicacionTormenta);
		
		Celda celdaMarineDentroDeTormenta = Juego.getInstance().getMapa().obtenerCelda(ubicacionMarineBajoTormenta);
		Celda celdaMarineFueraDeTormenta = Juego.getInstance().getMapa().obtenerCelda(ubicacionMarineFueraDeTormenta);
		assertTrue(celdaMarineDentroDeTormenta.contiene(marine));
		
		
		/*
		 * Pasa 1 turno
		 */
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertTrue(celdaMarineDentroDeTormenta.contiene(marine));
		
		/*
		 * Movemos al marine
		 */
		
		marine.moverse(ubicacionMarineFueraDeTormenta);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertTrue(celdaMarineFueraDeTormenta.contiene(marine));
		
		/*
		 * Y debería estar el marine vivo
		 */
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertTrue(celdaMarineFueraDeTormenta.contiene(marine));
	}

}
