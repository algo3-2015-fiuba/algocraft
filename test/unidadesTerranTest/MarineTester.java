package unidadesTerranTest;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.excepciones.UnidadEnEntrenamiento;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MarineTester {

	@Before 
	public void reiniciarJuego() {
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
		} catch (InicioInvalido ii) {}
		
		try {
			juego.iniciarJuego("mapas/test.map");
		} catch (InicioInvalido ii) {}
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testJugadorTerranCreaBarracaYDepositoSuministroYEntrenaUnMarine() throws Exception {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Marine marine = new Marine();
		Mapa mapa = Juego.getInstance().getMapa();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(0,1);
		Coordenada ubicacionPosibleMarine = new Coordenada(5,20);
		
		jugadorActual.recolectarMinerales(1000);
		jugadorActual.recolectarGasVespeno(1000);
		jugadorTerran.construir(barraca, ubicacionValidaBarraca);
		
		for (int i = 1; i < 13; i++) {
			
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			
		}
		
		jugadorTerran.construir(new DepositoSuministro(), ubicacionValidaDepositoSuministro);
		
		for (int i = 1; i < 7; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
		}
		
		barraca.entrenar(marine);
		
		for (int i = 1; i < 4; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertFalse(marine.entrenamientoFinalizado());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = Juego.getInstance().turnoDe();
		
		assertTrue(marine.entrenamientoFinalizado());
		assertFalse(mapa.obtenerCelda(ubicacionPosibleMarine).contiene(marine));
		
		//La unidad aun se encuentra en la barraca por lo que no posee una ubicacion fisica en el mapa.
		barraca.activarUnidad(marine, ubicacionPosibleMarine);
		
		assertTrue(mapa.obtenerCelda(ubicacionPosibleMarine).contiene(marine));
		
	}
	
	@Test
	public void testSiJugadorTerranEntrenaMarineEnBarracaNoPuedeActivarloHastaQueHayaFinalizadoSuEntrenamiento() throws Exception {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Marine marine = new Marine();
		Mapa mapa = Juego.getInstance().getMapa();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(0,1);
		Coordenada ubicacionPosibleMarine = new Coordenada(5,20);
		
		jugadorActual.recolectarMinerales(1000);
		jugadorActual.recolectarGasVespeno(1000);
		jugadorTerran.construir(barraca, ubicacionValidaBarraca);
		
		for (int i = 1; i < 13; i++) {
			
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			
		}
		
		jugadorTerran.construir(new DepositoSuministro(), ubicacionValidaDepositoSuministro);
		
		for (int i = 1; i < 7; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
		}
		
		barraca.entrenar(marine);
		
		for (int i = 1; i < 2; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			assertFalse(marine.entrenamientoFinalizado());
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = Juego.getInstance().turnoDe();
		
		assertFalse(marine.entrenamientoFinalizado());
		assertFalse(mapa.obtenerCelda(ubicacionPosibleMarine).contiene(marine));
		
		//La unidad aun se encuentra en la barraca por lo que no posee una ubicacion fisica en el mapa.
		exception.expect(UnidadEnEntrenamiento.class);
		barraca.activarUnidad(marine, ubicacionPosibleMarine);		
		
		assertFalse(mapa.obtenerCelda(ubicacionPosibleMarine).contiene(marine));
		
	}	
	
}
