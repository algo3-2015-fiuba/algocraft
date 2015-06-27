package movimientosTest;

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
import juego.razas.unidades.protoss.Scout;
import juego.razas.unidades.protoss.Zealot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class movimientoVoladorTest {

	@Before 
	public void reiniciarJuego() {
		
		Juego.reiniciar();
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
	public void testSiUnZealotSeMueveAUnLugarDeAireSaltaExcepcion() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion, NoTieneVision {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
			
		Juego.getInstance().turnoDe().finalizarTurno();		
		Jugador jugadorZealot = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionTierra = new Coordenada(30,3);
		Coordenada ubicacionAire = new Coordenada(31,3);
		
		
		Zealot zealot = new Zealot();
		jugadorZealot.asignarUnidad(zealot);		
		zealot.moverse(ubicacionTierra);
		
		exception.expect(UbicacionInvalida.class);
		zealot.moverse(ubicacionAire);
		
		assertFalse(mapa.obtenerCelda(ubicacionAire).contiene(zealot));
		
	}
	
	@Test
	public void testSiUnScoutSeMueveAUnLugarDeAireEstaBien() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion, NoTieneVision {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
			
		Juego.getInstance().turnoDe().finalizarTurno();		
		Jugador jugadorZealot = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionTierra = new Coordenada(30,3);
		Coordenada ubicacionAire = new Coordenada(31,3);
		
		
		Scout scout = new Scout();
		jugadorZealot.asignarUnidad(scout);		
		scout.moverse(ubicacionTierra);
		
		scout.moverse(ubicacionAire);
		
		assertTrue(mapa.obtenerCelda(ubicacionAire).contiene(scout));
		
	}	

}
