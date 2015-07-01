package magiasTest;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
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
		
		Juego.reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorTerran("jugadorTerran2", Color.blue));
		} catch (InicioInvalido ii) {}
		
		try {
			juego.iniciarJuego("mapas/test.map");
		} catch (InicioInvalido ii) {}
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSiUnMarineEstaIrradiadoPierdeVida() throws Exception {
		this.reiniciarJuego();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		jugadorAtacante.finalizarTurno();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		jugadorReceptor.asignarUnidad(marine);
		
		marine.moverse(ubicacionMarineEnemigo);
		jugadorReceptor.finalizarTurno();
		
		NaveCiencia naveCiencia = new NaveCiencia();
		jugadorAtacante.asignarUnidad(naveCiencia);
		naveCiencia.moverse(ubicacionNaveCiencia);
		
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
	public void testSiUnMarineEstaIrradiadoLastimaASusCercanos() throws Exception {
		this.reiniciarJuego();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		jugadorAtacante.finalizarTurno();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionMarineEnemigo2 = new Coordenada(1,20);
		
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		Marine marine2 = new Marine();
		jugadorReceptor.asignarUnidad(marine);
		jugadorReceptor.asignarUnidad(marine2);
		
		marine.moverse(ubicacionMarineEnemigo);
		marine2.moverse(ubicacionMarineEnemigo2);
		jugadorReceptor.finalizarTurno();
		
		NaveCiencia naveCiencia = new NaveCiencia();
		jugadorAtacante.asignarUnidad(naveCiencia);
		naveCiencia.moverse(ubicacionNaveCiencia);
		
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
	public void testSiDosMarinesEstanIrradiadosSeLastimanEntreSiAditivamente() throws Exception {
	this.reiniciarJuego();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		jugadorAtacante.finalizarTurno();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionMarineEnemigo2 = new Coordenada(1,20);
		
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		Marine marine2 = new Marine();
		jugadorReceptor.asignarUnidad(marine);
		jugadorReceptor.asignarUnidad(marine2);
		
		marine.moverse(ubicacionMarineEnemigo);
		marine2.moverse(ubicacionMarineEnemigo2);
		jugadorReceptor.finalizarTurno();
		
		NaveCiencia naveCiencia = new NaveCiencia();
		jugadorAtacante.asignarUnidad(naveCiencia);
		naveCiencia.moverse(ubicacionNaveCiencia);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		for (int i = 1; i < 10; i++) {
			jugadorAtacante.finalizarTurno();
			jugadorReceptor.finalizarTurno();
		}
		
		//El primer jugador Terran ataca a ambos
		
		naveCiencia.lanzarRadiacion(marine);
		naveCiencia.lanzarRadiacion(marine2);
		
		
		//El primer marine original no pierde vida		
		assertTrue(marine.vidaActual() == 40);
		
		//Al igual que el segundo	
		assertTrue(marine2.vidaActual() == 40);
		
		jugadorAtacante.finalizarTurno();
		
		//Deberia perder un 20% de su vida original (40), es decir recibir un danio de 16.		
		assertTrue(marine.vidaActual() == 24);
		
		//Tambien el segundo	
		assertTrue(marine2.vidaActual() == 24);
	}
}
