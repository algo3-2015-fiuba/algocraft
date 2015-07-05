package juegoTests;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.interfaces.excepciones.AccesoDenegado;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequerimientosInvalidos;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.protoss.Scout;
import juego.razas.unidades.terran.Marine;
import juego.razas.unidades.terran.NaveCiencia;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PoblacionTester {

	@Before 
	public void reiniciarJuego() {
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
			juego.iniciarJuego("mapas/test.map");
			
		} catch (InicioInvalido ii) {}
		
	}	
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSiScoutAtacaNaveCienciaEnemigaHastaDestruirlaDisminuyePoblacionEnemiga() throws Exception {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionNaveCienciaEnemigo = new Coordenada(0,20);
		Coordenada ubicacionScoutAtacante = new Coordenada(1,20);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		Scout scout = new Scout();
	
		jugadorAtacante.asignarUnidad(scout);
		scout.moverse(ubicacionScoutAtacante);
		
		jugadorAtacante.finalizarTurno();
		
		jugadorReceptor.asignarUnidad(naveCiencia);	
		naveCiencia.moverse(ubicacionNaveCienciaEnemigo);
		
		jugadorReceptor.finalizarTurno();
		
		assertTrue(jugadorReceptor.poblacionActual() == 2);
		assertTrue(jugadorAtacante.poblacionActual() == 3);
		
		for(int i = 0; i < 15; i++) {
			
			jugadorAtacante.finalizarTurno();
			jugadorReceptor.finalizarTurno();
			scout.atacarA(naveCiencia);
			
		}
		
		assertFalse(mapa.obtenerCelda(ubicacionNaveCienciaEnemigo).contiene(naveCiencia));
		
		assertTrue(jugadorReceptor.poblacionActual() == 0);
		assertTrue(jugadorAtacante.poblacionActual() == 3);
		
	}
	
	@Test
	public void testSiJugadorNoCreaDepositoSuministroEIntentaCrearMarineErrorSobrePoblacion() throws Exception {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Marine marine = new Marine();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		
		jugadorActual.recolectarMinerales(1000);
		jugadorActual.recolectarGasVespeno(1000);
		jugadorTerran.construir(barraca, ubicacionValidaBarraca);
		
		for (int i = 1; i < 13; i++) {
			
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			
		}
		
		exception.expect(SobrePoblacion.class);
		barraca.entrenar(marine);
		
	}
	
	@Test
	public void testSiJugadorAlcanzaLimiteDePoblacionNoPuedeCrearMasMarines() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion, AccesoDenegado {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(4,20);
		
		jugadorActual.recolectarMinerales(2000);
		jugadorActual.recolectarGasVespeno(2000);
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
		
		for (int i = 1; i < 6; i++) {
			barraca.entrenar(new Marine());
		}
		
		exception.expect(SobrePoblacion.class);
		barraca.entrenar(new Marine());
		
	}

}
