package supuestosTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.NoTieneVision;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.construcciones.protoss.Pilon;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.protoss.Zealot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ConstruccionesTester {

	@Before 
	public void reiniciarJuego() {
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
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
	public void testZealotAtacaDepositoSuministroTerranAunEnConstruccionYLoDestruye() 
			throws UbicacionInvalida, RecursosInsuficientes, SobrePoblacion, RequerimientosInvalidos, NoTieneVision {
		
		this.reiniciarJuego();
		Mapa mapa = Juego.getInstance().getMapa();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		JugadorProtoss jugadorProtoss = (JugadorProtoss)Juego.getInstance().turnoDe();
		Coordenada ubicacionValidaPilon = new Coordenada(0,20);
		Coordenada ubicacionValidaAcceso = new Coordenada(4,20);
		Coordenada ubicacionValidaZealot = new Coordenada(8,20);
		Pilon pilon = new Pilon();
		Acceso acceso = new Acceso();
		Zealot zealot = new Zealot();
		
		jugadorActual.recolectarMinerales(1000);
		jugadorActual.recolectarGasVespeno(1000);
		jugadorProtoss.construir(pilon, ubicacionValidaPilon);
		jugadorProtoss.construir(acceso, ubicacionValidaAcceso);
		
		for (int i = 1; i < 9; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		acceso.entrenar(zealot);
		
		for (int i = 1; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
		}
		
		assertFalse(mapa.obtenerCelda(ubicacionValidaZealot).contiene(zealot));
		
		acceso.activarUnidad(zealot, ubicacionValidaZealot);
		
		assertTrue(mapa.obtenerCelda(ubicacionValidaZealot).contiene(zealot));
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(9,20);
		DepositoSuministro depositoSuministro = new DepositoSuministro();
		
		jugadorActual.recolectarMinerales(1000);
		jugadorActual.recolectarGasVespeno(1000);
		jugadorTerran.construir(depositoSuministro, ubicacionValidaDepositoSuministro);
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
		
		assertTrue(mapa.obtenerCelda(ubicacionValidaDepositoSuministro).contiene(depositoSuministro));
		
		for (int i = 1; i < 64; i++) {
			zealot.atacarA(depositoSuministro);
		}
		
		assertFalse(mapa.obtenerCelda(ubicacionValidaDepositoSuministro).contiene(depositoSuministro));
		
	}
	
	@Test
	public void testNoTengoLimitesParaCrearDepositosDeSuministrosPeroElLimiteDePoblacionNoSuperaraLos200() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos {
			
		this.reiniciarJuego();
		Juego juego = Juego.getInstance();
		Jugador jugadorActual = juego.turnoDe();
		jugadorActual.finalizarTurno();
		
		jugadorActual = juego.turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		int x = 0;
		int y = 20;
			
		jugadorActual.recolectarMinerales(1000000);
			
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 30; j += 2) {
				jugadorTerran.construir(new DepositoSuministro(), new Coordenada(x+j,y+i));
			}
		}
		
		/*
		 * Mientras se construyen, no aumenta la poblacion
		 */
			
		for (int i = 0; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertEquals(0, jugadorActual.limiteDePoblacion());
				assertEquals(0, jugadorActual.poblacionActual());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = juego.turnoDe();
			
		assertEquals(200, jugadorActual.limiteDePoblacion());
		assertEquals(0, jugadorActual.poblacionActual());
		
	}
	
	
}
