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
import juego.razas.construcciones.terran.DepositoSuministro;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DepositoSuministroTester {

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
	public void testJugadorTerranCreaDepositoSuministroSatisfactoriamente() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValida = new Coordenada(0,1);
		
		/* El rango de celdas de un deposito de suministro debe ser de dos
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el deposito ocupara las celdas
		 * (0,0) y (1,0). 
		 * (0,0) = D
		 * (1,0) = X
		 * Grafico:
		 * - - - - - - 	
		 * - - D X - - 
		 * - - - - - -
		 */
		
		jugadorActual.construir(new DepositoSuministro(), ubicacionValida);
		
		for (int i = 1; i < 6; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertTrue(jugadorActual.poblacionActual() == 0);
				assertTrue(jugadorActual.poblacionMaxima() == 0);
			}
		
		}
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		// Pasaron 6 turnos desde que el jugador Terran construyo el deposito de suministro, 
		// por lo que la construccion deberia haber finalizado
		
		assertTrue(jugadorActual.poblacionActual() == 0);
		assertTrue(jugadorActual.poblacionMaxima() == 5);
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();

		//Verifico que el jugador Protoss no haya modificado su poblacion
		assertTrue(jugadorActual.poblacionActual() == 0);
		assertTrue(jugadorActual.poblacionMaxima() == 0);
		
	}

	@Test
	public void testSiCreoMasDe40DepositosDeSuministroElLimiteDePoblacionSigueSiendo200() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		int x = 0;
		int y = 20;
		
		jugadorActual.bolsaDeRecursos().recolectarMinerales(1000000);
		
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 30; j += 2) {
				jugadorActual.construir(new DepositoSuministro(), new Coordenada(x+j,y+i));
			}
		}
		
		for (int i = 0; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertEquals(0, jugadorActual.poblacionMaxima());
				assertEquals(0, jugadorActual.poblacionActual());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertEquals(200, jugadorActual.poblacionMaxima());
		assertEquals(0, jugadorActual.poblacionActual());
		
	}

	@Test
	public void testSiJugadorNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValida = new Coordenada(0,1);
		
		//El deposito de suministro vale 100 minerales, si gasto 110 de los 200 iniciales le quedan 90 minerales.
		jugadorActual.bolsaDeRecursos().consumirMinerales(160);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new DepositoSuministro(), ubicacionValida);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada coordenadaInvalida = new Coordenada(-10,3);
		
		//Una coordenada negativa no existe en ningun mapa.
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new DepositoSuministro(), coordenadaInvalida);
		
	}
	

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada ubicacionValida = new Coordenada(0,1);
		
		jugadorActual.construir(new DepositoSuministro(), ubicacionValida);
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new DepositoSuministro(), ubicacionValida);
		
	}


	@Test
	public void testSiLaCoordenadaIndicadaPoseeRecursosErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada ubicacionNodoGasVespeno = new Coordenada(1,0);

		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new DepositoSuministro(), ubicacionNodoGasVespeno);
		
	}
	
}
