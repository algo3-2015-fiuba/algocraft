package edificiosProtossTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.Controlable;
import juego.interfaces.commandConstrucciones.militares.ConstructorAcceso;
import juego.interfaces.commandConstrucciones.militares.ConstructorPuertoEstelar;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequiereAcceso;
import juego.interfaces.excepciones.RequiereBarraca;
import juego.interfaces.excepciones.RequiereFabrica;
import juego.interfaces.excepciones.RequierePuertoEstelar;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.razas.Protoss;
import juego.razas.Terran;
import juego.razas.protoss.construcciones.Acceso;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PuertoEstelarTester {

	@Before 
	public void reiniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		Acceso.reiniciar();
		
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		
		juego.iniciarJuego("mapas/test.map");
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testCreacionDePuertoEstelarSatisfactoria() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		
		/* El rango de celdas de un puerto estelar debe ser de seis
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el deposito ocupara las celdas
		 * (0,0), (1,0), (0,1), y (1,1). 
		 * (0,0) = D
		 * (1,0), (0,1), (1,1), (0,2), (1,2)  = X
		 * Grafico:
		 * - - - - - -
		 * - - X X - -
		 * - - X X - - 	
		 * - - D X - - 
		 * - - - - - -
		 */
		
		// Necesita 100 de gas vespeno para construir la fabrica, este metodo no se debe usar,
		// sirve para los test y para los recolectores.
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new ConstructorAcceso(), new Coordenada(0,20));
		
		for (int i = 0; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		//En el mapa 'test' la coordenada (0,1) es una coordenada valida para crear la barraca
		jugadorActual.construir(new ConstructorPuertoEstelar(), new Coordenada(0,1));
		
		// El metodo 'puedeConstruirMarine' verifica unicamente si hay una barraca activa,
		// no tiene en cuenta el costo mineral de construir un marine
		
		for (int i = 0; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorProtoss")) {
				assertFalse(mapa.obtenerCelda(coord).obtenerConstruible().construccionFinalizada());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(mapa.obtenerCelda(coord).obtenerConstruible().construccionFinalizada());
		
	}
	
	@Test
	public void testSiUnJugadorNoCreaUnAccesoNoPuedeCrearPuertoEstelarErrorRequiereAcceso() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);
		
		exception.expect(RequiereAcceso.class);
		jugadorActual.construir(new ConstructorPuertoEstelar(), new Coordenada(0,1));
		
	}
	
	@Test
	public void testSiJugadorTerranNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes, 
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new ConstructorAcceso(), new Coordenada(0,20));
		
		for (int i = 0; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		
		//El puerto estelar vale 150 minerales y 150 de gas vespeno, si no recolecto gas vespeno no podra construir.
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new ConstructorPuertoEstelar(), new Coordenada(0,1));
		
	}
	
	@Test
	public void testSiJugadorIndicaCoordenadaInvalidaErrorCoordenadaFueraDeRango() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new ConstructorAcceso(), new Coordenada(0,20));
		
		for (int i = 0; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		
		//Coloco una coordenada negativa, ya que los mapas no tienen un limite fijo, pero
		//si es negativa seguro no debe existir.
		exception.expect(CoordenadaFueraDeRango.class);
		jugadorActual.construir(new ConstructorPuertoEstelar(), new Coordenada(-10,3));
		
	}
	
	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() throws ColorInvalido, NombreInvalido, FaltanJugadores, 
	IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
	RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new ConstructorAcceso(), new Coordenada(0,20));
		
		for (int i = 0; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorActual.construir(new ConstructorPuertoEstelar(), new Coordenada(0,1));
		
		exception.expect(CeldaOcupada.class);
		jugadorActual.construir(new ConstructorPuertoEstelar(), new Coordenada(0,1));
		
	}
	
	@Test
	public void testSiUnJugadorEsPropietarioDeUnPuertoEstelarEsUnRecolectorAliado() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new ConstructorAcceso(), new Coordenada(0,20));
		
		for (int i = 0; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		jugadorActual.construir(new ConstructorPuertoEstelar(), coord);

		for (int i = 0; i < 10; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertTrue(construccion.esPropietario(jugadorActual));
		
	}
	
	@Test
	public void testSiUnJugadorNoEsPropietarioDeUnPuertoEstelarEsUnRecolectorEnemigo() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new ConstructorAcceso(), new Coordenada(0,20));
		
		for (int i = 0; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		
		jugadorActual.construir(new ConstructorPuertoEstelar(), coord);

		for (int i = 0; i < 11; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertFalse(construccion.esPropietario(jugadorActual));
		
	}
	
	@Test
	public void testSiUnJugadorTerranTrataDeMoverUnPuertoEstelarProtossErrorPropietarioInvalido() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido, RequiereAcceso, RequierePuertoEstelar,
			RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new ConstructorAcceso(), new Coordenada(0,20));
		
		for (int i = 0; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		
		jugadorActual.construir(new ConstructorPuertoEstelar(), coord);

		for (int i = 0; i < 11; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(PropietarioInvalido.class);
		construccion.moverse(new Coordenada(0,3));
		
	}
	
	@Test
	public void testSiUnJugadorProtossTrataDeMoverUnPuertoEstelarErrorConstruccionesNoSeMueven() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();

		jugadorActual.recolectarGasVespeno(1000);
		jugadorActual.recolectarMinerales(1000);
		
		jugadorActual.construir(new ConstructorAcceso(), new Coordenada(0,20));
		
		for (int i = 0; i < 8; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		
		jugadorActual.construir(new ConstructorPuertoEstelar(), coord);

		for (int i = 0; i < 11; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(ConstruccionesNoSeMueven.class);
		construccion.moverse(new Coordenada(0,1));
		
	}
	
}
