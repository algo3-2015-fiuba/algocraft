package edificiosTerranTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.Controlable;
import juego.interfaces.commandConstrucciones.recolectores.ConstructorRefineria;
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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RefineriaTester {

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
	public void testJugadorTerranCreaRefineriaEnNodoDeGasVespenoSatisfactoriamente() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, 
			ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RequiereAcceso, RequierePuertoEstelar,
			RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		/* La refineria se crea alrededor de las coordenadas centrales especificadas (x,y) 
		 * si existe un nodo de gas vespeno y no esta ocupado por ninguna construccion propia o enemiga.
		 * En la coordenada (1,0) del mapa 'test' existe un nodo de gas vespeno, por lo que es correcto crearlo en esta ubicacion.
		 */
		jugadorActual.construir(new ConstructorRefineria(), new Coordenada(1,0));

		for (int i = 0; i < 5; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorTerran")) {
				assertTrue(jugadorActual.recursos().getMineralesRecolectados() == 100);
				assertTrue(jugadorActual.recursos().getGasVespenoRecolectado() == 0);
			}
		
		}
		
		jugadorActual.finalizarTurno(); //Recolecto 10
		jugadorActual = juego.turnoDe();
		
		// Pasaron 6 turnos desde que el jugadorTerran creo la refineria,
		// por lo que la construccion deberia haber finalizado
		assertTrue(jugadorActual.recursos().getGasVespenoRecolectado() == 10);
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe(); //Recolecto 10
		
		assertTrue(jugadorActual.recursos().getGasVespenoRecolectado() == 0); //El jugador Protoss no modifica su cantidad de minerales
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe(); //Recolecto otros 10
		
		assertTrue(jugadorActual.recursos().getGasVespenoRecolectado() == 30);
		
		/* El jugador inicia el juego con 200 de mineral
		 * construir la refineria costa 100 minerales.
		 * Por turno recolecta, en un nodo con recursos,
		 * un total de 10 gas vespeno.
		 */
		
	}
	
	@Test
	public void testSiJugadorTerranNoPoseeSuficientesRecursosParaConstruirErrorRecursosInsuficientes() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes, 
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//La refineria vale 100 minerales, si gasto 160 de los 200 iniciales le quedan 40 gasVespeno.
		jugadorActual.recursos().consumirMinerales(160);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new ConstructorRefineria(), new Coordenada(1,0));
		
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
		jugadorActual.construir(new ConstructorRefineria(), new Coordenada(-10,3));
		
	}
	
	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaElJugadorNoPuedeConstruir() throws ColorInvalido, NombreInvalido, FaltanJugadores, 
	IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
	RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorRefineria(), new Coordenada(1,0));
		
		exception.expect(CeldaOcupada.class);
		jugadorActual.construir(new ConstructorRefineria(), new Coordenada(1,0));
		
	}
	
	@Test
	public void testSiLaCoordenadaIndicadaNoPoseeGasVespenoErrorUbicacionInvalida() throws ColorInvalido, NombreInvalido, FaltanJugadores, 
	IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
	RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		//La coordenada (0,0) en el mapa de pruebas siempre contiene un nodo de minerales
		
		exception.expect(UbicacionInvalida.class);
		jugadorActual.construir(new ConstructorRefineria(), new Coordenada(0,0));
		
	}
	
	@Test
	public void testSiUnProtossIntentaConstruirUnaRefineriaErrorImposibleConstruir() 
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
		jugadorActual.construir(new ConstructorRefineria(), new Coordenada(1,0));
		
	}
	
	@Test
	public void testSiUnJugadorEsPropietarioDeUnaRefineriaEsUnRecolectorAliado() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(1,0);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorRefineria(), coord);

		for (int i = 0; i < 6; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertTrue(construccion.esPropietario(jugadorActual));
		
	}
	
	@Test
	public void testSiUnJugadorNoEsPropietarioDeUnaRefineriaEsUnRecolectorEnemigo() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(1,0);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorRefineria(), coord);

		for (int i = 0; i < 7; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertFalse(construccion.esPropietario(jugadorActual));
		
	}
	
	@Test
	public void testSiUnJugadorProtossTrataDeMoverUnaRefineriaTerranErrorPropietarioInvalido() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido, RequiereAcceso, 
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(1,0);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorRefineria(), coord);

		for (int i = 0; i < 7; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(PropietarioInvalido.class);
		construccion.moverse(new Coordenada(4,0));
		
	}
	
	@Test
	public void testSiUnJugadorTerranTrataDeMoverUnaRefineriaErrorConstruccionesNoSeMueven() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido, RequiereAcceso, RequierePuertoEstelar, 
			RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(1,0);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorRefineria(), coord);

		for (int i = 0; i < 6; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(ConstruccionesNoSeMueven.class);
		construccion.moverse(new Coordenada(4,0));
		
	}

}
