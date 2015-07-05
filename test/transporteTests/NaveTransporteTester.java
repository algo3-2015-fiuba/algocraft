package transporteTests;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.protoss.Dragon;
import juego.razas.unidades.protoss.NaveTransporteProtoss;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NaveTransporteTester {

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
	public void testSiSuboDosDragonANaveDeTransporteNoTengoMasCapacidad() throws Exception {
		
		this.reiniciarJuego();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		jugadorTerran.finalizarTurno();
		JugadorProtoss jugadorProtoss = (JugadorProtoss)Juego.getInstance().turnoDe();
		NaveTransporteProtoss naveTransporteProtoss = new NaveTransporteProtoss();
		Dragon dragon1 = new Dragon();
		Dragon dragon2 = new Dragon();
		Dragon dragon3 = new Dragon();
		Coordenada ubicacionNaveTransporteValida = new Coordenada(0,20);
		Coordenada ubicacionDragon1Valida = new Coordenada(0,21);
		Coordenada ubicacionDragon2Valida = new Coordenada(1,21);
		Coordenada ubicacionDragon3Valida = new Coordenada(2,21);
		Mapa mapa = Juego.getInstance().getMapa();
		
		jugadorProtoss.asignarUnidad(naveTransporteProtoss);
		jugadorProtoss.asignarUnidad(dragon1);
		jugadorProtoss.asignarUnidad(dragon2);
		jugadorProtoss.asignarUnidad(dragon3);
		
		naveTransporteProtoss.moverse(ubicacionNaveTransporteValida);
		dragon1.moverse(ubicacionDragon1Valida);
		dragon2.moverse(ubicacionDragon2Valida);
		dragon3.moverse(ubicacionDragon3Valida);
		
		assertTrue(mapa.obtenerCelda(ubicacionNaveTransporteValida).contiene(naveTransporteProtoss));
		assertTrue(mapa.obtenerCelda(ubicacionDragon1Valida).contiene(dragon1));
		assertTrue(mapa.obtenerCelda(ubicacionDragon2Valida).contiene(dragon2));
	
		naveTransporteProtoss.transportar(dragon1);
		naveTransporteProtoss.transportar(dragon2);
	
		assertFalse(mapa.obtenerCelda(ubicacionDragon1Valida).contiene(dragon1));
		assertFalse(mapa.obtenerCelda(ubicacionDragon2Valida).contiene(dragon2));
		assertTrue(jugadorProtoss.esAliado(dragon1));
		assertTrue(jugadorProtoss.esAliado(dragon2));
		
		assertTrue(naveTransporteProtoss.capacidadActual() == 8);
		
		naveTransporteProtoss.transportar(dragon3);
		
		assertTrue(naveTransporteProtoss.capacidadActual() == 8);
		assertTrue(mapa.obtenerCelda(ubicacionDragon3Valida).contiene(dragon3));
		
	}
	
	@Test
	public void testSiSuboDosDragonANaveDeTransporteHastaQueNoBajeUnoNoPuedoSubirOtro() throws Exception {
		
		this.reiniciarJuego();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		jugadorTerran.finalizarTurno();
		JugadorProtoss jugadorProtoss = (JugadorProtoss)Juego.getInstance().turnoDe();
		NaveTransporteProtoss naveTransporteProtoss = new NaveTransporteProtoss();
		Dragon dragon1 = new Dragon();
		Dragon dragon2 = new Dragon();
		Dragon dragon3 = new Dragon();
		Coordenada ubicacionNaveTransporteValida = new Coordenada(0,20);
		Coordenada ubicacionDragon1Valida = new Coordenada(0,21);
		Coordenada ubicacionDragon2Valida = new Coordenada(1,21);
		Coordenada ubicacionDragon3Valida = new Coordenada(2,21);
		Coordenada descensoDragon1 = new Coordenada(0,22);
		Mapa mapa = Juego.getInstance().getMapa();
		
		jugadorProtoss.asignarUnidad(naveTransporteProtoss);
		jugadorProtoss.asignarUnidad(dragon1);
		jugadorProtoss.asignarUnidad(dragon2);
		jugadorProtoss.asignarUnidad(dragon3);
		
		naveTransporteProtoss.moverse(ubicacionNaveTransporteValida);
		dragon1.moverse(ubicacionDragon1Valida);
		dragon2.moverse(ubicacionDragon2Valida);
		dragon3.moverse(ubicacionDragon3Valida);
	
		naveTransporteProtoss.transportar(dragon1);
		naveTransporteProtoss.transportar(dragon2);
		
		assertFalse(mapa.obtenerCelda(descensoDragon1).contiene(dragon1));
	
		naveTransporteProtoss.bajar(dragon1, descensoDragon1);
		
		assertTrue(mapa.obtenerCelda(descensoDragon1).contiene(dragon1));
		assertTrue(naveTransporteProtoss.capacidadActual() == 4);
		
	}

}
