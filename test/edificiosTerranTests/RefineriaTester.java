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
import juego.razas.construcciones.terran.Refineria;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RefineriaTester {

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
	public void testJugadorTerranCreaRefineriaEnNodoSatisfactoriamenteYRecolectaGasVespeno() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada ubicacionGasVespeno = new Coordenada(1,0);
		jugadorActual.construir(new Refineria(), ubicacionGasVespeno);
		
		for (int i = 1; i < 6; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertTrue(jugadorActual.bolsaDeRecursos().getGasVespenoRecolectado() == 0);
			}
		
		}
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		// Pasaron 6 turnos desde que el jugador Terran construyo la refineria, 
		// por lo que la construccion deberia haber finalizado
		
		assertTrue(jugadorActual.bolsaDeRecursos().getGasVespenoRecolectado() == 10);
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		//El jugador Protoss no modifica su cantidad de gas vespeno
		assertTrue(jugadorActual.bolsaDeRecursos().getGasVespenoRecolectado() == 0);
		
		jugadorActual.finalizarTurno(); //Recolecto otros 10
		jugadorActual = juego.turnoDe();
		;
		assertTrue(jugadorActual.bolsaDeRecursos().getGasVespenoRecolectado() == 30);
		
	}

	
	@Test
	public void testSiJugadorNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//La refineria vale 100 minerales, si gasto 110 de los 200 iniciales le quedan 90 minerales.
		jugadorActual.bolsaDeRecursos().consumirMinerales(110);
		
		Coordenada ubicacionGasVespeno = new Coordenada(1,0);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new Refineria(), ubicacionGasVespeno);
		
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
		jugadorActual.construir(new Refineria(), coordenadaInvalida);
		
	}
	

	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada ubicacionNodoGasVespeno = new Coordenada(1,0);
		
		jugadorActual.construir(new Refineria(), ubicacionNodoGasVespeno);
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new Refineria(), ubicacionNodoGasVespeno);
		
	}


	@Test
	public void testSiLaCoordenadaIndicadaNoPoseeGasVespenoErrorUbicacionInvalida() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		Coordenada ubicacionNodoMinerales = new Coordenada(0,0);

		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new Refineria(), ubicacionNodoMinerales);
		
	}

}
