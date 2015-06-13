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
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.protoss.construcciones.Pilon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PilonTester {

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
	public void testCreacionCorrectaDeUnPilon() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValida = new Coordenada(0,1);
		
		/* El rango de celdas de un pilon debe ser de dos
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el pilon ocupara las celdas
		 * (0,0) y (1,0). 
		 * (0,0) = D
		 * (1,0) = X
		 * Grafico:
		 * - - - - - - 	
		 * - - D X - - 
		 * - - - - - -
		 */
		
		//En el caso del mapa 'test', la coordenada (0,1) cumple este requisito.
		jugadorActual.construir(new Pilon(), ubicacionValida);
		
		for (int i = 0; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorProtoss")) {
				assertEquals(0, jugadorActual.limiteDePoblacion());
				assertEquals(0, jugadorActual.poblacionActual());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertEquals(5, jugadorActual.limiteDePoblacion());
		assertEquals(0, jugadorActual.poblacionActual());
		
	}

	@Test
	public void testSiCreoMasDe40PilonesElLimiteDePoblacionSigueSiendo200() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		int x = 0;
		int y = 20;
		
		jugadorActual.recolectarMinerales(1000000);
		
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 30; j += 2) {
				jugadorActual.construir(new Pilon(), new Coordenada(x+j,y+i));
			}
		}
		
		for (int i = 0; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorProtoss")) {
				assertEquals(0, jugadorActual.limiteDePoblacion());
				assertEquals(0, jugadorActual.poblacionActual());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertEquals(200, jugadorActual.limiteDePoblacion());
		assertEquals(0, jugadorActual.poblacionActual());
		
	}
	
	@Test
	public void testSiUnJugadorTerranNoPuedeCrearUnPilonSiHayRecursosEncimaErrorImposibleConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionNodoMineral = new Coordenada(0,0);
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new Pilon(), ubicacionNodoMineral);
		
	}
	
	@Test
	public void testSiUnJugadorTrataDeCrearUnPilonPeroNoTieneSuficientesRecursosErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValida = new Coordenada(0,1);
		//Un pilon costa un total de 100 minerales, si inicia con 200 no deberian alcanzarle.
		jugadorActual.consumirMinerales(110);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new Pilon(), ubicacionValida);
		
	}
	
	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionInvalida = new Coordenada(-10,3);

		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new Pilon(), ubicacionInvalida);
		
	}
	
	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaNoSePuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Coordenada ubicacionValida = new Coordenada(0,1);
		
		jugadorActual.construir(new Pilon(), ubicacionValida);
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new Pilon(), ubicacionValida);
	
	}

}
