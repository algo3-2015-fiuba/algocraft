package edificiosProtossTests;

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
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.protoss.construcciones.Acceso;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccesoTester {

	@Before 
	public void reiniciarJuego() {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
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
	public void testCreacionDeAccesoSatisfactoriamente() 
			throws UbicacionInvalida, RecursosInsuficientes, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		Coordenada ubicacionValida = new Coordenada(0,1);
		Acceso nuevoAcceso = new Acceso();
		
		/* El rango de celdas de un acceso debe ser de cuatro
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el deposito ocupara las celdas
		 * (0,0), (1,0), (0,1), y (1,1). 
		 * (0,0) = D
		 * (1,0), (0,1), (1,1) = X
		 * Grafico:
		 * - - - - - -
		 * - - X X - - 	
		 * - - D X - - 
		 * - - - - - -
		 */
		
		assertFalse(mapa.obtenerCelda(ubicacionValida).poseeConstruible());
		jugadorActual.construir(nuevoAcceso, ubicacionValida);
			
		for (int i = 1; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorProtoss")) {
				assertFalse(nuevoAcceso.construccionFinalizada());
				assertTrue(mapa.obtenerCelda(ubicacionValida).poseeConstruible());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(nuevoAcceso.construccionFinalizada());
		assertTrue(mapa.obtenerCelda(ubicacionValida).poseeConstruible());
		
	}

	@Test
	public void testSiAccesoFinalizaConstruccionPuertoEstelarHabilitado() 
			throws UbicacionInvalida, RecursosInsuficientes, RequerimientosInvalidos {
	
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		Coordenada ubicacionValida = new Coordenada(0,1);
		Acceso nuevoAcceso = new Acceso();
				
		assertFalse(mapa.obtenerCelda(ubicacionValida).poseeConstruible());
		jugadorActual.construir(nuevoAcceso, ubicacionValida);
			
		assertFalse(((JugadorProtoss)jugadorActual).puertoEstelarHabilitado());
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		assertTrue(((JugadorProtoss)jugadorActual).puertoEstelarHabilitado());
		
	}

	@Test
	public void testSiJugadorNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValida = new Coordenada(0,1);
		
		//El Acceso vale 150 minerales, si gasto 60 de los 200 iniciales le quedan 140 minerales.
		jugadorActual.bolsaDeRecursos().consumirMinerales(60);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new Acceso(), ubicacionValida);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada coordenadaInvalida = new Coordenada(-10,3);
		
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new Acceso(), coordenadaInvalida);
		
	}
	

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.bolsaDeRecursos().recolectarMinerales(300);
		
		Coordenada ubicacionValida = new Coordenada(0,1);
		
		jugadorActual.construir(new Acceso(), ubicacionValida);
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new Acceso(), ubicacionValida);
		
	}


	@Test
	public void testSiLaCoordenadaIndicadaPoseeRecursosErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada ubicacionNodoGasVespeno = new Coordenada(1,0);

		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new Acceso(), ubicacionNodoGasVespeno);
		
	}

}
