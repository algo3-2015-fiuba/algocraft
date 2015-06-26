package edificiosTerranTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.terran.CentroDeMineral;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CentroDeMineralTester {
	
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
	public void testJugadorTerranCreaCentroDeMineralesEnNodoSatisfactoriamenteYRecolectaMinerales() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionNodoMineral = new Coordenada(0,0);
		
		jugadorActual.construir(new CentroDeMineral(), ubicacionNodoMineral);
		
		for (int i = 1; i < 4; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertTrue(jugadorActual.getMineralesRecolectados() == 150);
			}
		
		}
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		// Pasaron 4 turnos desde que el jugador Terran construyo el centro de mineral, 
		// por lo que la construccion deberia haber finalizado
		
		assertTrue(jugadorActual.getMineralesRecolectados() == 160);
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		//El jugador Protoss no modifica su cantidad de minerales
		assertTrue(jugadorActual.getMineralesRecolectados() == 200);
		
		jugadorActual.finalizarTurno(); //Recolecto otros 10
		jugadorActual = juego.turnoDe();
		;
		assertTrue(jugadorActual.getMineralesRecolectados() == 180);
		
	}

	
	@Test
	public void testSiJugadorNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//El centro de mineral vale 50 minerales, si gasto 160 de los 200 iniciales le quedan 40 minerales.
		jugadorActual.consumirMinerales(160);
		
		Coordenada ubicacionNodoMineral = new Coordenada(0,0);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new CentroDeMineral(), ubicacionNodoMineral);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada coordenadaInvalida = new Coordenada(-10,3);
		
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new CentroDeMineral(), coordenadaInvalida);
		
	}
	

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada ubicacionNodoMineral = new Coordenada(0,0);
		
		jugadorActual.construir(new CentroDeMineral(), ubicacionNodoMineral);
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new CentroDeMineral(), ubicacionNodoMineral);
		
	}


	@Test
	public void testSiLaCoordenadaIndicadaNoPoseeMineralesErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada ubicacionNodoGasVespeno = new Coordenada(1,0);

		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new CentroDeMineral(), ubicacionNodoGasVespeno);
		
	}

}