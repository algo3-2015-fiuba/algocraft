package edificiosProtossTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.commandConstructor.recolectores.ConstructorNexoMineral;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
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

public class NexoMineralTester {
	
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
	public void testJugadorProtossConstruyeNexoMineralEnNodoMineralSatisfactoriamente() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, 
			ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		/* El nexo mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (0,0) del mapa 'test' existe un nodo mineral, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorActual.construir(new ConstructorNexoMineral(), new Coordenada(0,0));

		for (int i = 0; i < 9; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorProtoss")) {
				assertTrue(jugadorActual.getMineralesRecolectados() == 150);
			}
		
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		//Pasaron 4 turnos del jugador Protoss, por lo que la construccion del nexo mineral deberia haber finalizado
		
		assertTrue(jugadorActual.getMineralesRecolectados() == 160);
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(jugadorActual.getMineralesRecolectados() == 170);
		
		/* El jugador inicia el juego con 200 de mineral
		 * construir el nexo mineral costa 50 minerales.
		 * Por turno recolecta, en un nodo con recursos,
		 * un total de 10 minerales.
		 */
		
	}
	
	@Test
	public void testSiJugadorProtossNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes, 
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//El nexo de mineral vale 50 minerales, si gasto 160 de los 200 iniciales le quedan 40 minerales.
		jugadorActual.consumirMinerales(160);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new ConstructorNexoMineral(), new Coordenada(0,0));
		
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
		jugadorActual.construir(new ConstructorNexoMineral(), new Coordenada(-10,3));
		
	}
	
	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() throws ColorInvalido, NombreInvalido, FaltanJugadores, 
	IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorNexoMineral(), new Coordenada(0,0));
		
		exception.expect(CeldaOcupada.class);
		jugadorActual.construir(new ConstructorNexoMineral(), new Coordenada(0,0));
		
	}
	
	@Test
	public void testSiLaCoordenadaIndicadaNoPoseeMineralesErrorUbicacionInvalida() throws ColorInvalido, NombreInvalido, FaltanJugadores, 
	IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//La coordenada (1,0) en el mapa de pruebas siempre contiene gas vespeno
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new ConstructorNexoMineral(), new Coordenada(1,0));
		
	}
	
	@Test
	public void testSiUnTerranIntentaConstruirUnNexoMineralErrorImposibleConstruir() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		if (!jugadorActual.suNombreEs("jugadorTerran")) { 
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(ImposibleConstruir.class);
		jugadorActual.construir(new ConstructorNexoMineral(), new Coordenada(0,0));
		
	}
	
	@Test
	public void testSiUnJugadorEsPropietarioDeUnNexoMineralEsUnRecolectorAliado() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,0);
		Jugador jugadorActual = juego.turnoDe();
		

		jugadorActual.construir(new ConstructorNexoMineral(), coord);

		for (int i = 0; i < 9; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(mapa.obtenerCelda(coord).obtenerControlableEnTierra().esPropietario(jugadorActual));
		
	}
	
	@Test
	public void testSiUnJugadorNoEsPropietarioDeUnNexoMineralEsUnRecolectorEnemigo() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,0);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorNexoMineral(), coord);

		for (int i = 0; i < 9; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		assertFalse(mapa.obtenerCelda(coord).obtenerControlableEnTierra().esPropietario(jugadorActual));
		
	}
	
	@Test
	public void testSiUnJugadorTerranTrataDeMoverUnNexoMineralProtossErrorPropietarioInvalido() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,0);
		Jugador jugadorActual = juego.turnoDe();
			
		jugadorActual.construir(new ConstructorNexoMineral(), coord);

		for (int i = 0; i < 9; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			
		}
		
		exception.expect(PropietarioInvalido.class);
		mapa.obtenerCelda(coord).obtenerControlableEnTierra().moverse(new Coordenada(0,4));
		
	}
	
	@Test
	public void testSiUnJugadorProtossTrataDeMoverUnNexoMineralErrorConstruccionesNoSeMueven() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,0);
		Jugador jugadorActual = juego.turnoDe();

		jugadorActual.construir(new ConstructorNexoMineral(), coord);

		for (int i = 0; i < 9; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		exception.expect(ConstruccionesNoSeMueven.class);
		mapa.obtenerCelda(coord).obtenerControlableEnTierra().moverse(new Coordenada(0,4));
		
	}
	
}
