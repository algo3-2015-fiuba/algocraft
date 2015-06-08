package edificiosTerranTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.commandConstructor.almacenadores.ConstructorDepositoSuministro;
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
import org.junit.Test;

public class DepositoSuministroTester {

	@Before 
	public void reiniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		
		juego.iniciarJuego("mapas/test.map");
		
	}
	
	@Test
	public void testCreacionCorrectaDeUnDepositoSuministro() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, 
			CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		
		/* El rango de celdas de un deposito de suministro debe ser de dos
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el deposito ocupara las celdas
		 * (0,0) y (1,0). 
		 * Grafico:
		 * - - - - - - 	
		 * - - X X - - 
		 * - - - - - -
		 */
		
		//En el caso del mapa 'test', la coordenada (0,1) cumple este requisito.
		jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(0,1));
		
		for (int i = 0; i < 11; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorTerran")) {
				assertEquals(0, jugadorActual.poblacionAlmacenable());
				assertEquals(0, jugadorActual.poblacionActual());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();

		assertEquals(5, jugadorActual.poblacionAlmacenable());
		assertEquals(0, jugadorActual.poblacionActual());
		
	}
	
	
	
}
