package edificiosTerranTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.RequiereFabrica;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.Fabrica;
import juego.razas.construcciones.terran.PuertoEstelarTerran;

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
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
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
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaFabrica = new Coordenada(4,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(0,1);
		PuertoEstelarTerran nuevoPuertoEstelar = new PuertoEstelarTerran();
		
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

		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorTerran.construir(nuevoPuertoEstelar, ubicacionValidaPuertoEstelar);
		
		// El metodo 'puedeConstruirMarine' verifica unicamente si hay una barraca activa,
		// no tiene en cuenta el costo mineral de construir un marine
		
		for (int i = 0; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertFalse(nuevoPuertoEstelar.construccionFinalizada());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(nuevoPuertoEstelar.construccionFinalizada());
		
	}

	@Test
	public void testSiUnJugadorTerranTrataDeCrearUnPuertoEstelarPeroNoPoseeFabricaErrorRequiereFabrica()
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaPuertoEstelar =  new Coordenada(0,1);
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		exception.expect(RequiereFabrica.class);
		jugadorTerran.construir(new PuertoEstelarTerran(), ubicacionValidaPuertoEstelar);
		
	}
	
	@Test
	public void testSiJugadorTerranNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaFabrica = new Coordenada(4,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(0,1);
		
		jugadorActual.recolectarGasVespeno(100);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(RecursosInsuficientes.class);
		jugadorTerran.construir(new PuertoEstelarTerran(), ubicacionValidaPuertoEstelar);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaFabrica = new Coordenada(4,20);
		Coordenada ubicacionInvalidaPuertoEstelar = new Coordenada(-10,3);
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorTerran.construir(new PuertoEstelarTerran(), ubicacionInvalidaPuertoEstelar);
		
	}

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaFabrica = new Coordenada(4,20);
		Coordenada ubicacionValidaPuertoEstelar = new Coordenada(-10,3);
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);

		jugadorTerran.construir(new Barraca(), ubicacionValidaBarraca);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorTerran.construir(new Fabrica(), ubicacionValidaFabrica);
		
		for (int i = 0; i < 12; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorTerran.construir(new PuertoEstelarTerran(), ubicacionValidaPuertoEstelar);
		
		exception.expect(UbicacionInvalida.class);
		jugadorTerran.construir(new PuertoEstelarTerran(), ubicacionValidaPuertoEstelar);
		
	}
	
}
