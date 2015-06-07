package edificiosTerranTests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import juego.Juego;
import juego.excepciones.*;
import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.*;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.razas.*;


public class CentroDeMineralTest {
	
	@Before 
	public void iniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores {
		
		Juego juego = Juego.getInstance(); 
		
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		
		//Se genera un mapa, se inicializan los datos, etc...
		juego.iniciarJuego();
	}
	
	@Test
	public void testJugadorTerranCreaCentroDeMineralEnNodoDeMineralesSatisfactoriamente() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		
		Juego juego = Juego.getInstance();
		
		Jugador jugadorActual = juego.turnoDe();
		
		//Esto es simplemente para asegurarme que estoy testeando sobre el jugador de raza terran
		//No existe ningun metodo similar implementado en el juego.
		if (!jugadorActual.suNombreEs("jugadorTerran")) { 
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		// El centro de mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		// si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		jugadorActual.construir(new ConstructorCentroDeMineral(), new Coordenada(10,12));

		for (int i = 1; i < 8; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorTerran")) {
				assertFalse(jugadorActual.recolectoMinerales());
			}
		
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		//Pasaron 4 turnos del jugador Terran, por lo que la construccion del centro de mineral deberia haber finalizado
		
		assertTrue(jugadorActual.recolectoMinerales());
		
		/* El jugador inicia el juego con 200 de mineral
		 * construir el centro de mineral costa 50 minerales.
		 * Por turno recolecta, en un nodo con recursos,
		 * un total de 10 minerales.
		 */
		
	}
	
}