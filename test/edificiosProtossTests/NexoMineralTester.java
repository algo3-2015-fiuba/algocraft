package edificiosProtossTests;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.protoss.NexoMineral;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NexoMineralTester {
	
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
	public void testJugadorConstruyeNexoMineralEnNodoMineralSatisfactoriamente() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionNodoMineral = new Coordenada(0,0);
		
		jugadorProtoss.construir(new NexoMineral(), ubicacionNodoMineral);

		for (int i = 1; i < 4; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorProtoss")) {
				assertTrue(jugadorActual.getMineralesRecolectados() == 150);
			}
		
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe(); //Recolecto 10
		
		assertTrue(jugadorActual.getMineralesRecolectados() == 160);
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe(); //Recolecto 10
		
		//El jugador Terran no modifica su cantidad de minerales
		assertTrue(jugadorActual.getMineralesRecolectados() == 200); 
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe(); //Recolecto otros 10
		
		assertTrue(jugadorActual.getMineralesRecolectados() == 180);
		
	}

	@Test
	public void testSiJugadorProtossNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionNodoMineral = new Coordenada(0,0);

		jugadorProtoss.consumirMinerales(160);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorProtoss.construir(new NexoMineral(), ubicacionNodoMineral);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionInvalida = new Coordenada(-10,3);

		exception.expect(CoordenadaFueraDeRango.class);
		jugadorProtoss.construir(new NexoMineral(), ubicacionInvalida);
		
	}
	
	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionNodoMineral = new Coordenada(0,0);
		
		jugadorProtoss.construir(new NexoMineral(), ubicacionNodoMineral);
		
		exception.expect(UbicacionInvalida.class);
		jugadorProtoss.construir(new NexoMineral(), ubicacionNodoMineral);
		
	}
	
	@Test
	public void testSiLaCoordenadaIndicadaNoPoseeMineralesErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionGasVespeno = new Coordenada(1,0);

		exception.expect(UbicacionInvalida.class);
		jugadorProtoss.construir(new NexoMineral(), ubicacionGasVespeno);
		
	}
	
}
