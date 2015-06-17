package magiasTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.unidades.terran.Marine;
import juego.razas.unidades.terran.NaveCiencia;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class naveCienciaTest {

	@Before 
	public void reiniciarJuego() {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorTerran("jugadorTerran2", Color.blue));
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
	public void testSiUnMarineEstaIrradiadoPierdeVida() throws UbicacionInvalida, RecursosInsuficientes {
		this.reiniciarJuego();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		jugadorAtacante.finalizarTurno();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarineEnemigo);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.moverse(ubicacionNaveCiencia);
		
		jugadorReceptor.asignarUnidad(marine);	
		jugadorAtacante.asignarUnidad(naveCiencia);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		for (int i = 1; i < 2; i++) {
			jugadorAtacante.finalizarTurno();
			jugadorReceptor.finalizarTurno();
		}
		
		//El primer jugador Terran ataca
		
		naveCiencia.lanzarRadiacion(marine);
		
		//No deberia perder vida
		
		assertEquals(marine.vidaActual(), 40, 0.001);
		
		jugadorAtacante.finalizarTurno();
		
		//Deberia perder un 20% de su vida, 32
		
		assertEquals(marine.vidaActual(), 32, 0.001);
	}
	
	@Test
	public void testSiUnMarineEstaIrradiadoLastimaASusCercanos() throws UbicacionInvalida, RecursosInsuficientes {
		this.reiniciarJuego();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		jugadorAtacante.finalizarTurno();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionMarineEnemigo2 = new Coordenada(1,20);
		
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarineEnemigo);
		Marine marine2 = new Marine();
		marine2.moverse(ubicacionMarineEnemigo2);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.moverse(ubicacionNaveCiencia);
		
		jugadorReceptor.asignarUnidad(marine);
		jugadorReceptor.asignarUnidad(marine2);
		jugadorAtacante.asignarUnidad(naveCiencia);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		for (int i = 1; i < 2; i++) {
			jugadorAtacante.finalizarTurno();
			jugadorReceptor.finalizarTurno();
		}
		
		//El primer jugador Terran ataca
		
		naveCiencia.lanzarRadiacion(marine);
		
		//El primer marine original no pierde vida		
		assertEquals(marine.vidaActual(), 40, 0.001);
		
		//Al igual que el segundo	
		assertEquals(marine2.vidaActual(), 40, 0.001);
		
		jugadorAtacante.finalizarTurno();
		
		//Deberia perder un 20% de su vida, 32		
		assertEquals(marine.vidaActual(), 32, 0.001);
		
		//Tambien el segundo	
		assertEquals(marine2.vidaActual(), 32, 0.001);
	}
	
	@Test
	public void testSiDosMarinesEstanIrradiadosSeLastimanEntreSiAditivamente() throws UbicacionInvalida, RecursosInsuficientes {
	this.reiniciarJuego();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		jugadorAtacante.finalizarTurno();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionMarineEnemigo2 = new Coordenada(1,20);
		
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarineEnemigo);
		Marine marine2 = new Marine();
		marine2.moverse(ubicacionMarineEnemigo2);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.moverse(ubicacionNaveCiencia);
		
		jugadorReceptor.asignarUnidad(marine);
		jugadorReceptor.asignarUnidad(marine2);
		jugadorAtacante.asignarUnidad(naveCiencia);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		for (int i = 1; i < 10; i++) {
			jugadorAtacante.finalizarTurno();
			jugadorReceptor.finalizarTurno();
		}
		
		//El primer jugador Terran ataca a ambos
		
		naveCiencia.lanzarRadiacion(marine);
		naveCiencia.lanzarRadiacion(marine2);
		
		
		//El primer marine original no pierde vida		
		assertEquals(marine.vidaActual(), 40, 0.001);
		
		//Al igual que el segundo	
		assertEquals(marine2.vidaActual(), 40, 0.001);
		
		jugadorAtacante.finalizarTurno();
		
		//Deberia perder un 20% de su vida, dos veces: 26		
		assertEquals(marine.vidaActual(), 26, 0.001);
		
		//Tambien el segundo	
		assertEquals(marine2.vidaActual(), 26, 0.001);
	}
}
