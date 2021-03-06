package edificiosTerranTests;

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
import juego.razas.construcciones.terran.Refineria;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RefineriaTester {

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
	public void testJugadorTerranCreaRefineriaEnNodoSatisfactoriamenteYRecolectaGasVespeno() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		Coordenada ubicacionGasVespeno = new Coordenada(1,0);
		jugadorTerran.construir(new Refineria(), ubicacionGasVespeno);
		
		for (int i = 1; i < 6; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			assertTrue(jugadorTerran.getGasVespenoRecolectado() == 0);
		
		}
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		// Pasaron 6 turnos desde que el jugador Terran construyo la refineria, 
		// por lo que la construccion deberia haber finalizado
		
		assertTrue(jugadorActual.getGasVespenoRecolectado() == 10);
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		//El jugador Protoss no modifica su cantidad de gas vespeno
		assertTrue(jugadorActual.getGasVespenoRecolectado() == 0);
		
		jugadorActual.finalizarTurno(); //Recolecto otros 10
		jugadorActual = juego.turnoDe();
		;
		assertTrue(jugadorActual.getGasVespenoRecolectado() == 30);
		
	}

	
	@Test
	public void testSiJugadorNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		
		//La refineria vale 100 minerales, si gasto 110 de los 200 iniciales le quedan 90 minerales.
		jugadorTerran.consumirMinerales(110);
		
		Coordenada ubicacionGasVespeno = new Coordenada(1,0);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorTerran.construir(new Refineria(), ubicacionGasVespeno);
		
	}

	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		
		Coordenada coordenadaInvalida = new Coordenada(-10,3);
		
		//Una coordenada negativa no existe en ningun mapa.
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorTerran.construir(new Refineria(), coordenadaInvalida);
		
	}
	

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		
		Coordenada ubicacionNodoGasVespeno = new Coordenada(1,0);
		
		jugadorTerran.construir(new Refineria(), ubicacionNodoGasVespeno);
		
		exception.expect(UbicacionInvalida.class);
		jugadorTerran.construir(new Refineria(), ubicacionNodoGasVespeno);
		
	}


	@Test
	public void testSiLaCoordenadaIndicadaNoPoseeGasVespenoErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		JugadorTerran jugadorTerran = (JugadorTerran) juego.turnoDe();
		
		Coordenada ubicacionNodoMinerales = new Coordenada(0,0);

		exception.expect(UbicacionInvalida.class);
		jugadorTerran.construir(new Refineria(), ubicacionNodoMinerales);
		
	}

}
