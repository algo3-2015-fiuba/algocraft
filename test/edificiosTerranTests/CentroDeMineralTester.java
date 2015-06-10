package edificiosTerranTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import juego.Juego;
import juego.excepciones.*;
import juego.interfaces.Controlable;
import juego.interfaces.commandConstrucciones.recolectores.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.*;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.razas.*;


public class CentroDeMineralTester {
	
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
	public void testJugadorTerranCreaCentroDeMineralEnNodoDeMineralesSatisfactoriamente() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, 
			ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		/* El centro de mineral se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de minerales y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (0,0) del mapa 'test' existe un nodo mineral, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorActual.construir(new ConstructorCentroDeMineral(), new Coordenada(0,0));

		for (int i = 0; i < 3; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorTerran")) {
				assertTrue(jugadorActual.recursos().getMineralesRecolectados() == 150);
			}
		
		}
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		// Pasaron 4 turnos desde que el jugador Terran construyo el centro de mineral, 
		// por lo que la construccion deberia haber finalizado
		
		assertTrue(jugadorActual.recursos().getMineralesRecolectados() == 160);
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		assertTrue(jugadorActual.recursos().getMineralesRecolectados() == 200); //El jugador Protoss no modifica su cantidad de minerales
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe(); //Recolecto otros 10
		
		assertTrue(jugadorActual.recursos().getMineralesRecolectados() == 180);
		
		/* El jugador inicia el juego con 200 de mineral
		 * construir el centro de mineral costa 50 minerales.
		 * Por turno recolecta, en un nodo con recursos,
		 * un total de 10 minerales.
		 */
		
	}
	
	@Test
	public void testCreoUnCentroDeMineralYAgregoZealotsHastaElLimiteDePoblacion() {
		
	}
	
	@Test
	public void testSiJugadorTerranNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes, 
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//El centro de mineral vale 50 minerales, si gasto 160 de los 200 iniciales le quedan 40 minerales.
		jugadorActual.recursos().consumirMinerales(160);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new ConstructorCentroDeMineral(), new Coordenada(0,0));
		
	}
	
	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//Coloco una coordenada negativa, ya que los mapas no tienen un limite fijo, pero
		//si es negativa seguro no debe existir.
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new ConstructorCentroDeMineral(), new Coordenada(-10,3));
		
	}
	
	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() throws ColorInvalido, NombreInvalido, FaltanJugadores, 
	IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
	RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorCentroDeMineral(), new Coordenada(0,0));
		
		exception.expect(CeldaOcupada.class);
		jugadorActual.construir(new ConstructorCentroDeMineral(), new Coordenada(0,0));
		
	}
	
	@Test
	public void testSiLaCoordenadaIndicadaNoPoseeMineralesErrorUbicacionInvalida() throws ColorInvalido, NombreInvalido, FaltanJugadores, 
	IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
	RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//La coordenada (1,0) en el mapa de pruebas siempre contiene gas vespeno
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new ConstructorCentroDeMineral(), new Coordenada(1,0));
		
	}
	
	@Test
	public void testSiUnProtossIntentaConstruirUnCentroDeMineralErrorImposibleConstruir() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		if (!jugadorActual.suNombreEs("jugadorProtoss")) { 
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		exception.expect(ImposibleConstruir.class);
		jugadorActual.construir(new ConstructorCentroDeMineral(), new Coordenada(0,0));
		
	}
	
	@Test
	public void testSiUnJugadorEsPropietarioDeUnCentroDeMineralEsUnRecolectorAliado() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,0);
		Jugador jugadorActual = juego.turnoDe();

		jugadorActual.construir(new ConstructorCentroDeMineral(), coord);

		for (int i = 0; i < 4; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertTrue(construccion.esPropietario(jugadorActual));
		
	}
	
	@Test
	public void testSiUnJugadorNoEsPropietarioDeUnCentroDeMineralEsUnRecolectorEnemigo() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,0);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorCentroDeMineral(), coord);

		for (int i = 0; i < 5; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertFalse(construccion.esPropietario(jugadorActual));
		
	}
	
	@Test
	public void testSiUnJugadorProtossTrataDeMoverUnCentroDeMineralTerranErrorPropietarioInvalido() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido, RequiereAcceso, RequierePuertoEstelar,
			RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,0);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorCentroDeMineral(), coord);

		for (int i = 0; i < 5; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(PropietarioInvalido.class);
		construccion.moverse(new Coordenada(0,4));
		
	}
	
	@Test
	public void testSiUnJugadorTerranTrataDeMoverUnCentroDeMineralErrorConstruccionesNoSeMueven() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido, RequiereAcceso, RequierePuertoEstelar,
			RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,0);
		Jugador jugadorActual = juego.turnoDe();

		jugadorActual.construir(new ConstructorCentroDeMineral(), coord);

		for (int i = 0; i < 5; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(ConstruccionesNoSeMueven.class);
		construccion.moverse(new Coordenada(0,4));
		
	}
	
}