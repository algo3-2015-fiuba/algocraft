package edificiosTerranTests;

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
import juego.interfaces.excepciones.RequiereBarraca;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.terran.construcciones.Barraca;
import juego.razas.terran.construcciones.Fabrica;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FabricaTester {

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
	public void testCreacionDeFabricaSatisfactoria() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Fabrica nuevaFabrica = new Fabrica();
		
		/* El rango de celdas de una fabrica debe ser de seis
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

		jugadorActual.bolsaDeRecursos().recolectarGasVespeno(1000);
		jugadorActual.bolsaDeRecursos().recolectarMinerales(1000);
		
		jugadorActual.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorActual.construir(nuevaFabrica, ubicacionValidaFabrica);
		
		for (int i = 0; i < 11; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertFalse(nuevaFabrica.construccionFinalizada());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(nuevaFabrica.construccionFinalizada());
		
	}
	
	@Test
	public void testSiNoCreoBarracaAntesNoPuedoCrearFabrica() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);

		jugadorActual.bolsaDeRecursos().recolectarGasVespeno(200);
		
		exception.expect(RequiereBarraca.class);
		jugadorActual.construir(new Fabrica(), ubicacionValidaFabrica);
		
	}
	
	@Test
	public void testSiCreoFabricaHabilitoPuertoEstelar() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		
		jugadorActual.bolsaDeRecursos().recolectarGasVespeno(1000);
		jugadorActual.bolsaDeRecursos().recolectarMinerales(1000);
		
		jugadorActual.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorActual.construir(new Fabrica(), ubicacionValidaFabrica);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		assertTrue(((JugadorTerran)jugadorActual).puertoEstelarHabilitado());
		
	}
	
	@Test
	public void testSiJugadorTerranNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);
		
		jugadorActual.construir(new Barraca(), ubicacionValidaBarraca);

		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new Fabrica(), ubicacionValidaFabrica);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionInvalida = new Coordenada(-10,3);
		
		
		jugadorActual.bolsaDeRecursos().recolectarGasVespeno(1000);
		jugadorActual.bolsaDeRecursos().recolectarMinerales(1000);
		
		jugadorActual.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new Fabrica(), ubicacionInvalida);
		
	}

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);
		
		jugadorActual.bolsaDeRecursos().recolectarGasVespeno(1000);
		jugadorActual.bolsaDeRecursos().recolectarMinerales(1000);
		
		jugadorActual.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorActual.construir(new Fabrica(), ubicacionValidaFabrica);
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new Fabrica(), ubicacionValidaFabrica);
		
	}

}
