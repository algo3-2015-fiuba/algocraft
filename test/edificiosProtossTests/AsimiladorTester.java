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
import juego.razas.construcciones.protoss.Asimilador;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AsimiladorTester {

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
	public void testJugadorProtossCreaAsimiladorEnNodoDeGasVespenoSatisfactoriamente() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionNodoGasVespeno = new Coordenada(1,0);
		
		jugadorProtoss.construir(new Asimilador(), ubicacionNodoGasVespeno);

		for (int i = 1; i < 6; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorProtoss")) {
				assertTrue(jugadorActual.getMineralesRecolectados() == 100);
				assertTrue(jugadorActual.getGasVespenoRecolectado() == 0);
			}
		
		}
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		assertTrue(jugadorActual.getGasVespenoRecolectado() == 10);
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe(); 
		
		//El jugador Terran no modifica su cantidad de minerales
		assertTrue(jugadorActual.getGasVespenoRecolectado() == 0);
		
		jugadorActual.finalizarTurno(); //Recolecto otros 10
		jugadorActual = juego.turnoDe();		
		assertTrue(jugadorActual.getGasVespenoRecolectado() == 30);
		
	}

	@Test
	public void testSiJugadorProtossNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionNodoGasVespeno = new Coordenada(1,0);
		//El Asimilador vale 100 minerales, si gasto 160 de los 200 iniciales le quedan 40 gasVespeno.
		jugadorActual.consumirMinerales(160);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorProtoss.construir(new Asimilador(), ubicacionNodoGasVespeno);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionInvalida =  new Coordenada(-10,3);

		exception.expect(CoordenadaFueraDeRango.class);
		jugadorProtoss.construir(new Asimilador(), ubicacionInvalida);
		
	}

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionValida = new Coordenada(1,0);
		
		jugadorProtoss.construir(new Asimilador(), ubicacionValida);
		
		exception.expect(UbicacionInvalida.class);
		jugadorProtoss.construir(new Asimilador(), ubicacionValida);
		
	}

	@Test
	public void testSiLaCoordenadaIndicadaNoPoseeGasVespenoErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) juego.turnoDe();
		Coordenada ubicacionNodoMinerales = new Coordenada(0,0);
		
		exception.expect(UbicacionInvalida.class);
		jugadorProtoss.construir(new Asimilador(), ubicacionNodoMinerales);
		
	}

}
