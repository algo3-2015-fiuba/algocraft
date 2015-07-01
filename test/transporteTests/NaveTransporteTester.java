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
import juego.razas.unidades.protoss.NaveTransporte;

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
		NaveTransporte naveTransporteProtoss = new NaveTransporte();
		Dragon dragon1 = new Dragon();
		Dragon dragon2 = new Dragon();
		Coordenada ubicacionNaveTransporteValida = new Coordenada(0,20);
		Coordenada ubicacionDragon1Valida = new Coordenada(0,21);
		Coordenada ubicacionDragon2Valida = new Coordenada(1,21);
		Mapa mapa = Juego.getInstance().getMapa();
		
		jugadorProtoss.asignarUnidad(naveTransporteProtoss);
		jugadorProtoss.asignarUnidad(dragon1);
		jugadorProtoss.asignarUnidad(dragon2);
		
		naveTransporteProtoss.moverse(ubicacionNaveTransporteValida);
		dragon1.moverse(ubicacionDragon1Valida);
		dragon2.moverse(ubicacionDragon2Valida);
		
		assertTrue(mapa.obtenerCelda(ubicacionNaveTransporteValida).contiene(naveTransporteProtoss));
		assertTrue(mapa.obtenerCelda(ubicacionDragon1Valida).contiene(dragon1));
		assertTrue(mapa.obtenerCelda(ubicacionDragon2Valida).contiene(dragon2));
	
		naveTransporteProtoss.transportar(dragon1);
		naveTransporteProtoss.transportar(dragon2);
	
		assertFalse(mapa.obtenerCelda(ubicacionDragon1Valida).contiene(dragon1));
		assertFalse(mapa.obtenerCelda(ubicacionDragon2Valida).contiene(dragon2));
		assertTrue(jugadorProtoss.esAliado(dragon1));
		assertTrue(jugadorProtoss.esAliado(dragon2));
		
	}

}
