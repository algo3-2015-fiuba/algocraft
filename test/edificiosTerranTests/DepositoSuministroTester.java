package edificiosTerranTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.Controlable;
import juego.interfaces.commandConstrucciones.habitables.ConstructorDepositoSuministro;
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

public class DepositoSuministroTester {

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
	public void testCreacionCorrectaDeUnDepositoSuministro() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, 
			CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		
		/* El rango de celdas de un deposito de suministro debe ser de dos
		 * teniendo como coordenada determinante a la ingresada.
		 * Si la misma es, por ejemplo, (0,0) el deposito ocupara las celdas
		 * (0,0) y (1,0). 
		 * (0,0) = D
		 * (1,0) = X
		 * Grafico:
		 * - - - - - - 	
		 * - - D X - - 
		 * - - - - - -
		 */
		
		//En el caso del mapa 'test', la coordenada (0,1) cumple este requisito.
		jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(0,1));
		
		for (int i = 0; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorTerran")) {
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
	public void testSiCreoMasDe40DepositosDeSuministroElLimiteDePoblacionSigueSiendo200() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		int x = 0;
		int y = 20;
		
		//Este metodo no debe utilizarse y lo usan los recolectores, pero para probar el test es util.
		jugadorActual.recolectarMinerales(1000000);
		
		//En el caso del mapa 'test', a partir de la coordenada (0,20) se cumple este requisito.
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 30; j += 2) {
				jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(x+j,y+i));
			}
		}
		
		for (int i = 0; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.suNombreEs("jugadorTerran")) {
				assertEquals(0, jugadorActual.limiteDePoblacion());
				assertEquals(0, jugadorActual.poblacionActual());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertEquals(200, jugadorActual.limiteDePoblacion());
		assertEquals(0, jugadorActual.poblacionActual());
		
	}
	
	@Test
	public void testSiDestruyenUnDepositoDeSuministroPeroElJugadorTieneMasDe40SuLimiteDePoblacionSigueSiendo200() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes, 
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		Mapa mapa = juego.getMapa();
		int x = 0;
		int y = 20;
		
		// Este metodo no debe utilizarse y lo usan los recolectores para que el jugador recolecte los minerales que ya recogieron,
		// pero para probar el test es util.
		jugadorActual.recolectarMinerales(1000000);
		
		//En el caso del mapa 'test', a partir de la coordenada (0,20) se cumple este requisito.
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 30; j += 2) {
				jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(x+j,y+i));
			}
		}
		
		for (int i = 0; i < 6; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		assertEquals(200, jugadorActual.limiteDePoblacion());
		assertEquals(0, jugadorActual.poblacionActual());
		
		// Los construibles y unidades no se deben eliminar de esta forma,
		// pero para probar el test es util el metodo.
		mapa.obtenerCelda(new Coordenada(0,20)).removerConstruible();
		mapa.obtenerCelda(new Coordenada(1,20)).removerConstruible();
		
		assertEquals(200, jugadorActual.limiteDePoblacion());
		assertEquals(0, jugadorActual.poblacionActual());
		
	}
	
	@Test
	public void testCreoUnDepositoSuministroYAgregoMarinesHastaElLimiteDePoblacion() {
		
	}
	
	@Test
	public void testSiUnJugadorTerranNoPuedeCrearUnDepositoSuministroSiHayRecursosEncimaErrorImposibleConstruir() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		exception.expect(CeldaOcupada.class);
		jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(0,0));
		
	}
	
	@Test
	public void testSiUnJugadorProtossTrataDeCrearUnDepositoSuministroErrorImposibleConstruir() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes,
			UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe(); //Jugador Protoss
		
		exception.expect(ImposibleConstruir.class);
		jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(0,1));
		
	}
	
	@Test
	public void testSiUnJugadorTrataDeCrearUnDepositoSuministroPeroNoTieneSuficientesRecursosErrorRecursosInsuficientes() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
	
		//Un Deposito Suministro costa un total de 100 minerales, si inicia con 200 no deberian alcanzarle.
		jugadorActual.consumirMinerales(110);
		
		exception.expect(RecursosInsuficientes.class);
		jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(0,1));
		
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
		jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(-10,3));
		
	}
	
	@Test
	public void testSiLaCeldaFuePreviamenteOcupadaNoSePuedeConstruir() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada,
			RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(0,1));
		
		exception.expect(CeldaOcupada.class);
		jugadorActual.construir(new ConstructorDepositoSuministro(), new Coordenada(0,1));
	
	}

	@Test
	public void testSiUnJugadorEsPropietarioDelDepositoSuministroEsAliado() 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
	
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
	
		jugadorActual.construir(new ConstructorDepositoSuministro(), coord);
	
		for (int i = 0; i < 6; i++) {		
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
			CeldaOcupada, ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RequiereAcceso,
			RequierePuertoEstelar, RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
	
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
	
		jugadorActual.construir(new ConstructorDepositoSuministro(), coord);
	
		for (int i = 0; i < 7; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
		
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		assertFalse(construccion.esPropietario(jugadorActual));
	
	}
	
	@Test
	public void testSiUnJugadorProtossTrataDeMoverUnDepositoSuministroTerranErrorPropietarioInvalido() 
			throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, 
			RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango,
			CeldaOcupada, ConstruccionesNoSeMueven, PropietarioInvalido, RequiereAcceso, RequierePuertoEstelar,
			RequiereBarraca, RequiereFabrica {
		
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();
		
		jugadorActual.construir(new ConstructorDepositoSuministro(), coord);

		for (int i = 0; i < 7; i++) {
		
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
		Coordenada coord = new Coordenada(0,1);
		Jugador jugadorActual = juego.turnoDe();

		jugadorActual.construir(new ConstructorDepositoSuministro(), coord);

		for (int i = 0; i < 6; i++) {		
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();		
		}
				
		Celda celda = mapa.obtenerCelda(coord);
		Controlable construccion = (Controlable)(celda.obtenerConstruible());
		
		exception.expect(ConstruccionesNoSeMueven.class);
		construccion.moverse(new Coordenada(0,1));
		
	}
	
}
