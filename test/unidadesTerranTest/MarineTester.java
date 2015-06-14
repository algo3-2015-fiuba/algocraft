package unidadesTerranTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MarineTester {

	@Before 
	public void reiniciarJuego() {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
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
	public void testJugadorCreaBarracaYDepositoSuministroYEntrenaUnMarine() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Marine marine = new Marine();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(4,20);
		
		jugadorActual.bolsaDeRecursos().recolectarMinerales(1000);
		jugadorActual.bolsaDeRecursos().recolectarGasVespeno(1000);
		jugadorActual.construir(barraca, ubicacionValidaBarraca);
		
		for (int i = 1; i < 13; i++) {
			
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			
		}
		
		jugadorActual.construir(new DepositoSuministro(), ubicacionValidaDepositoSuministro);
		
		for (int i = 1; i < 7; i++) {
		
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
		
		}
		
		//La barraca coloca al marine, cuando su entrenamiento a finalizado, en la casilla mas cercana disponible.
		jugadorActual.entrenar(barraca, marine);
		
		for (int i = 1; i < 4; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			if (jugadorActual.getNombre().equals("jugadorTerran")) {
				assertFalse(marine.entrenamientoFinalizado());
			}
		}
		
		jugadorActual.finalizarTurno();
		jugadorActual = Juego.getInstance().turnoDe();
		
		System.out.println(jugadorActual.getNombre());
		assertTrue(marine.entrenamientoFinalizado());
		
	}
	
	@Test
	public void testSiJugadorNoCreaDepositoSuministroEIntentaCrearMarineErrorSobrePoblacion() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Marine marine = new Marine();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		
		jugadorActual.bolsaDeRecursos().recolectarMinerales(1000);
		jugadorActual.bolsaDeRecursos().recolectarGasVespeno(1000);
		jugadorActual.construir(barraca, ubicacionValidaBarraca);
		
		for (int i = 1; i < 13; i++) {
			
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			
		}
		
		exception.expect(SobrePoblacion.class);
		jugadorActual.entrenar(barraca, marine);
		
	}
	
}
