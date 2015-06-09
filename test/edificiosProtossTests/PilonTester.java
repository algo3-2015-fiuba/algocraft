package edificiosProtossTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.Controlable;
import juego.interfaces.commandConstructor.almacenadores.ConstructorPilon;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.razas.Protoss;
import juego.razas.Terran;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PilonTester {

	@Before 
	public void reiniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		
		juego.iniciarJuego("mapas/test.map");
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testCreacionCorrectaDeUnPilon() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, 
			CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		
		/* El rango de celdas de un pilon debe ser de dos
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el pilon ocupara las celdas
		 * (0,0) y (1,0). 
		 * Grafico:
		 * - - - - - - 	
		 * - - X X - - 
		 * - - - - - -
		 */
		
		//En el caso del mapa 'test', la coordenada (0,1) cumple este requisito.
		jugadorActual.construir(new ConstructorPilon(), new Coordenada(0,1));
		
		for (int i = 0; i < 11; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorProtoss")) {
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
	public void testSiUnJugadorTerranNoPuedeCrearUnPilonSiHayRecursosEncimaErrorImposibleConstruir() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		exception.expect(CeldaOcupada.class);
		jugadorActual.construir(new ConstructorPilon(), new Coordenada(0,0));
		
	}
	
	@Test
	public void testSiUnJugadorTerranTrataDeCrearUnDepositoSuministroErrorImposibleConstruir() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe(); //Jugador Terran
		
		exception.expect(ImposibleConstruir.class);
		jugadorActual.construir(new ConstructorPilon(), new Coordenada(0,1));
		
	}
	
	@Test
	public void testSiUnJugadorTrataDeCrearUnPilonPeroNoTieneSuficientesRecursosErrorRecursosInsuficientes() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
	
		//Un pilon costa un total de 100 minerales, si inicia con 200 no deberian alcanzarle.
		jugadorActual.consumirMinerales(110);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new ConstructorPilon(), new Coordenada(0,1));
		
	}
	
	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//Coloco una coordenada negativa, ya que los mapas no tienen un limite fijo, pero
		//si es negativa seguro no debe existir.
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new ConstructorPilon(), new Coordenada(-10,3));
		
	}
	
	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaNoSePuedeConstruir() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorPilon(), new Coordenada(0,1));
		
		exception.expect(CeldaOcupada.class);
		jugadorActual.construir(new ConstructorPilon(), new Coordenada(0,1));
	
	}

	@Test
	public void testSiUnJugadorEsPropietarioDelDepositoSuministroEsAliado() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		this.reiniciarJuego();
	
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
	
		jugadorActual.construir(new ConstructorPilon(), coord);
	
		for (int i = 0; i < 12; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertTrue(construccion.esPropietario(jugadorActual));
	
	}
	
	@Test
	public void testSiUnJugadorNoEsPropietarioDelDepositoSuministroEsEnemigo() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		this.reiniciarJuego();
	
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
	
		jugadorActual.construir(new ConstructorPilon(), coord);
	
		for (int i = 0; i < 13; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertFalse(construccion.esPropietario(jugadorActual));
	
	}
	
	@Test
	public void testSiUnJugadorTerranTrataDeMoverUnPilonProtossErrorPropietarioInvalido() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorPilon(), coord);

		for (int i = 0; i < 13; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(PropietarioInvalido.class);
		construccion.moverse(new Coordenada(0,4));
		
	}
	
	@Test
	public void testSiUnJugadorProtossTrataDeMoverUnCentroDeMineralErrorConstruccionesNoSeMueven() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();

		jugadorActual.construir(new ConstructorPilon(), coord);

		for (int i = 0; i < 12; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
				
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(ConstruccionesNoSeMueven.class);
		construccion.moverse(new Coordenada(0,1));
		
	}
	
}
