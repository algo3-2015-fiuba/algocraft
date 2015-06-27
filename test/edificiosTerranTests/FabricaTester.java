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
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.Fabrica;

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
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
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

		jugadorTerran.recolectarGasVespeno(1000);
		jugadorTerran.recolectarMinerales(1000);
		
		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			Jugador j = juego.turnoDe();
			j.finalizarTurno();
		}
		
		jugadorTerran.construir(nuevaFabrica, ubicacionValidaFabrica);
		
		for (int i = 0; i < 11; i++) {
			
			Jugador j = juego.turnoDe();
			j.finalizarTurno();

			assertFalse(nuevaFabrica.construccionFinalizada());
			
		}
		
		jugadorTerran.finalizarTurno();
		
		assertTrue(nuevaFabrica.construccionFinalizada());
		
	}
	
	@Test
	public void testSiNoCreoBarracaAntesNoPuedoCrearFabrica() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);

		jugadorTerran.recolectarGasVespeno(200);
		
		exception.expect(RequiereBarraca.class);
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
	}
	
	@Test
	public void testSiCreoFabricaHabilitoPuertoEstelar() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		
		jugadorTerran.recolectarGasVespeno(1000);
		jugadorTerran.recolectarMinerales(1000);
		
		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			Jugador j = juego.turnoDe();
			j.finalizarTurno();
		}
		
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
		for (int i = 0; i < 12; i++) {
			Jugador j = juego.turnoDe();
			j.finalizarTurno();
		}
		
		assertTrue(((JugadorTerran)jugadorTerran).puertoEstelarHabilitado());
		
	}
	
	@Test
	public void testSiJugadorTerranNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);
		
		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);

		for (int i = 0; i < 12; i++) {
			Jugador j = juego.turnoDe();
			j.finalizarTurno();
		}
		
		exception.expect(RecursosInsuficientes.class);
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionInvalida = new Coordenada(-10,3);
		
		
		jugadorTerran.recolectarGasVespeno(1000);
		jugadorTerran.recolectarMinerales(1000);
		
		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			Jugador j = juego.turnoDe();
			j.finalizarTurno();
		}
		
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorTerran.construir(new Fabrica(), ubicacionInvalida);
		
	}

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaFabrica = new Coordenada(0,1);
		
		jugadorTerran.recolectarGasVespeno(1000);
		jugadorTerran.recolectarMinerales(1000);
		
		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			Jugador j = juego.turnoDe();
			j.finalizarTurno();
		}
		
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
		exception.expect(UbicacionInvalida.class);
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
	}

}
