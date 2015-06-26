package edificiosProtossTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequiereAcceso;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.construcciones.protoss.PuertoEstelar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PuertoEstelarTester {

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
	public void testCreacionDePuertoEstelarSatisfactoria() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaAcceso = new Coordenada(0,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(0,1);
		PuertoEstelar nuevoPuertoEstelar = new PuertoEstelar();
		
		/* El rango de celdas de un puerto estelar debe ser de seis
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el deposito ocupara las celdas
		 * (0,0), (1,0), (0,1), y (1,1). 
		 * (0,0) = D
		 * (1,0), (0,1), (1,1), (0,2), (1,2)  = X
		 * Grafico:
		 * - - - - - -
		 * - - X X - -
		 * - - X X - - 	
		 * - - D X - - 
		 * - - - - - -
		 */
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		jugadorActual.construir(new Acceso(), ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorActual.construir(nuevoPuertoEstelar, ubicacionValidaPuertoEstelar);
		
		for (int i = 1; i < 10; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorProtoss")) {
				assertFalse(nuevoPuertoEstelar.construccionFinalizada());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(nuevoPuertoEstelar.construccionFinalizada());
		
	}

	@Test
	public void testSiUnJugadorProtossTrataDeCrearUnPuertoEstelarPeroNoPoseeAccesoErrorRequiereAcceso()
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaPuertoEstelar =  new Coordenada(0,1);
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		exception.expect(RequiereAcceso.class);
		jugadorActual.construir(new PuertoEstelar(), ubicacionValidaPuertoEstelar);
		
	}
	
	@Test
	public void testSiJugadorNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaAcceso = new Coordenada(0,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(0,1);
		
		jugadorActual.recolectarGasVespeno(100);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new Acceso(), ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new PuertoEstelar(), ubicacionValidaPuertoEstelar);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaAcceso = new Coordenada(0,20);
		Coordenada ubicacionInvalidaPuertoEstelar = new Coordenada(-10,3);
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		jugadorActual.construir(new Acceso(), ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new PuertoEstelar(), ubicacionInvalidaPuertoEstelar);
		
	}

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaAcceso = new Coordenada(0,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(-10,3);
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		jugadorActual.construir(new Acceso(), ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new PuertoEstelar(), ubicacionValidaPuertoEstelar);
		
	}

}
