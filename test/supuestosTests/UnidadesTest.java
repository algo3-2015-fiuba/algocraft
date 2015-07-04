package supuestosTests;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UnidadesTest {

	@Before 
	public void reiniciarJuego() {
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
		} catch (InicioInvalido ii) {}
		
		try {
			juego.iniciarJuego("mapas/small.map");
		} catch (InicioInvalido ii) {}
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testUnaUnidadNoPuedeCaminarPorUnaZonaDeRecursos() throws Exception {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Marine marine = new Marine();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,1);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(0,20);
		Coordenada ubicacionPosibleMovimientoMarine = new Coordenada(2,1);
		Coordenada ubicacionConRecursos = new Coordenada(1,0);
		
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
		
		for (int i = 1; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
		}
		
		barraca.activarUnidad(marine, ubicacionPosibleMovimientoMarine);
		
		exception.expect(UbicacionInvalida.class);
		marine.moverse(ubicacionConRecursos);
		
	}
	
	@Test
	public void testSiPierdoUnidadPeroPoseeoConstruccionPierdoVisionDeEsaUnidadPeroNoPierdoVisionDeCelda() throws Exception {
		
		this.reiniciarJuego();
		Mapa mapa = Juego.getInstance().getMapa();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Marine marine = new Marine();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,21);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(0,20);
		Coordenada ubicacionPosibleMovimientoMarine = new Coordenada(0,23);
		Coordenada ubicacionCercanaVistaPorMarine = new Coordenada(1,23);
		Celda celdaUbicacionMarine = mapa.obtenerCelda(ubicacionPosibleMovimientoMarine);
		Celda celdaCercanaAMarine = mapa.obtenerCelda(ubicacionCercanaVistaPorMarine);
		
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
		
		for (int i = 1; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
		}
		
		
		barraca.activarUnidad(marine, ubicacionPosibleMovimientoMarine);
		
		assertTrue(celdaUbicacionMarine.observadaPor(marine));
		assertTrue(celdaCercanaAMarine.observadaPor(marine));
		assertTrue(celdaUbicacionMarine.observadaPor(barraca));
		assertTrue(celdaCercanaAMarine.observadaPor(barraca));
		
		jugadorTerran.fallecido(marine);
		
		//Posee aun la vision de la barraca, que incluye la ubicacion del marine.
		assertTrue(jugadorTerran.tieneVision(celdaUbicacionMarine));
		assertFalse(celdaUbicacionMarine.observadaPor(marine));
		assertFalse(celdaCercanaAMarine.observadaPor(marine));
		assertTrue(celdaUbicacionMarine.observadaPor(barraca));
		
	}
	
	@Test
	public void testSiPierdoVisionDeConstruccionPierdoCeldasVistas() throws Exception {
		
		this.reiniciarJuego();
		Mapa mapa = Juego.getInstance().getMapa();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,21);
		Coordenada ubicacionCercanaABarraca = new Coordenada(3,21);
		Celda celdaUbicacionPrincipalBarraca = mapa.obtenerCelda(ubicacionValidaBarraca);
		Celda celdaUbicacionCercanaBarraca = mapa.obtenerCelda(ubicacionCercanaABarraca);
		
		jugadorActual.recolectarMinerales(1000);
		jugadorActual.recolectarGasVespeno(1000);
		jugadorTerran.construir(barraca, ubicacionValidaBarraca);
		
		for (int i = 1; i < 13; i++) {
			
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			
		}
		
		assertTrue(celdaUbicacionPrincipalBarraca.observadaPor(barraca));
		assertTrue(celdaUbicacionCercanaBarraca.observadaPor(barraca));
		assertTrue(jugadorTerran.tieneVision(barraca));
		assertTrue(jugadorTerran.tieneVision(celdaUbicacionPrincipalBarraca));
		assertTrue(jugadorTerran.tieneVision(celdaUbicacionCercanaBarraca));
		
		jugadorTerran.fallecido(barraca);

		assertFalse(celdaUbicacionPrincipalBarraca.observadaPor(barraca));
		assertFalse(celdaUbicacionCercanaBarraca.observadaPor(barraca));
		assertFalse(jugadorTerran.tieneVision(barraca));
		assertFalse(jugadorTerran.tieneVision(celdaUbicacionPrincipalBarraca));
		assertFalse(jugadorTerran.tieneVision(celdaUbicacionCercanaBarraca));
		
	}
	
}
