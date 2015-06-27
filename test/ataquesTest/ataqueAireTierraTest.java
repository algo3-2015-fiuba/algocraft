package ataquesTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.NoTieneVision;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.excepciones.AtaqueInvalido;
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.NaveCiencia;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ataqueAireTierraTest {

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
	public void testUnZealotNoPuedeAtacarAUnaNaveCiencia() 
			throws RecursosInsuficientes, UbicacionInvalida, SobrePoblacion, NoTieneVision, AtaqueInvalido {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionNaveCienciaEnemigo = new Coordenada(0,20);
		Coordenada ubicacionZealotAtacante = new Coordenada(1,20);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.moverse(ubicacionNaveCienciaEnemigo);
		
		Zealot zealot = new Zealot();
		zealot.moverse(ubicacionZealotAtacante);
		
		jugadorReceptor.asignarUnidad(naveCiencia);	
		jugadorAtacante.asignarUnidad(zealot);
		
		for(int i = 0; i < 50; i++)
			zealot.atacarA(naveCiencia);
		
		assertTrue(mapa.obtenerCelda(ubicacionNaveCienciaEnemigo).contiene(naveCiencia));
		
	}

}
