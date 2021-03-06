package edificiosProtossTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
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
import juego.razas.construcciones.protoss.PuertoEstelarProtoss;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PuertoEstelarTester {

	@Before 
	public void reiniciarJuego() {
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
		} catch (InicioInvalido ii) {}
		
		try {
			juego.iniciarJuego("mapas/test.map");
		} catch (InicioInvalido ii) {}
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testCreacionDePuertoEstelarSatisfactoria() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionValidaAcceso = new Coordenada(0,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(0,1);
		PuertoEstelarProtoss nuevoPuertoEstelar = new PuertoEstelarProtoss();
		
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

		jugadorProtoss.construir(new Acceso(), ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorProtoss.construir(nuevoPuertoEstelar, ubicacionValidaPuertoEstelar);
		
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
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionValidaPuertoEstelar =  new Coordenada(0,1);
		
		jugadorProtoss.recolectarGasVespeno(1000);
		jugadorProtoss.recolectarMinerales(1000);

		exception.expect(RequiereAcceso.class);
		jugadorProtoss.construir(new PuertoEstelarProtoss(), ubicacionValidaPuertoEstelar);
		
	}
	
	@Test
	public void testSiJugadorNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionValidaAcceso = new Coordenada(0,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(0,1);
		
		jugadorActual.recolectarGasVespeno(100);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorProtoss.construir(new Acceso(), ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(RecursosInsuficientes.class);
		jugadorProtoss.construir(new PuertoEstelarProtoss(), ubicacionValidaPuertoEstelar);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionValidaAcceso = new Coordenada(0,20);
		Coordenada ubicacionInvalidaPuertoEstelar = new Coordenada(-10,3);
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		jugadorProtoss.construir(new Acceso(), ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorProtoss.construir(new PuertoEstelarProtoss(), ubicacionInvalidaPuertoEstelar);
		
	}

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionValidaAcceso = new Coordenada(0,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(-10,3);
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		jugadorProtoss.construir(new Acceso(), ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(UbicacionInvalida.class);
		jugadorProtoss.construir(new PuertoEstelarProtoss(), ubicacionValidaPuertoEstelar);
		
	}

}
