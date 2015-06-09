package edificiosTerranTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.commandConstructor.habitables.ConstructorDepositoSuministro;
import juego.interfaces.commandConstructor.militares.ConstructorBarraca;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.Protoss;
import juego.razas.Terran;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BarracaTester {

	@Before 
	public void reiniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		
		juego.iniciarJuego("mapas/test.map");
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testCreacionDeBarracaSatisfactoria() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		/* El rango de celdas de una barraca debe ser de cuatro
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el deposito ocupara las celdas
		 * (0,0), (1,0), (0,1), y (1,1). 
		 * (0,0) = D
		 * (1,0), (0,1), (1,1) = X
		 * Grafico:
		 * - - - - - -
		 * - - X X - - 	
		 * - - D X - - 
		 * - - - - - -
		 */
		
		//En el mapa 'test' la coordenada (0,1) es una coordenada valida para crear la barraca
		jugadorActual.construir(new ConstructorBarraca(), new Coordenada(0,1));
		
		// El metodo 'puedeConstruirMarine' verifica unicamente si hay una barraca activa,
		// no tiene en cuenta el costo mineral de construir un marine
		
		for (int i = 0; i < 23; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorTerran")) {
				assertFalse(jugadorActual.puedeCrearMarines());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(jugadorActual.puedeCrearMarines());
		
	}

}
