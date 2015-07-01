package magiasTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Iterator;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.protoss.AltoTemplario;
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.Golliat;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class altoTemplarioTest {

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
	public void testSiUnMarineEstaBajoUnaTormentaMuere() throws Exception {
		
		this.reiniciarJuego();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarine = new Coordenada(0,20);
		Coordenada ubicacionAltoTemplario = new Coordenada(8,21);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarine);
		
		AltoTemplario altoTemplario = new AltoTemplario();
		altoTemplario.moverse(ubicacionAltoTemplario);
		
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorReceptor.asignarUnidad(marine);
		
		
		for (int i = 0; i < 5; i++) {
			Juego.getInstance().turnoDe().finalizarTurno();
		}
		
		Celda celdaMarine = Juego.getInstance().getMapa().obtenerCelda(ubicacionMarine);
		assertTrue(celdaMarine.contiene(marine));
		
		altoTemplario.lanzarTormentaPsionica(ubicacionMarine);
				
		Juego.getInstance().turnoDe().finalizarTurno();
		assertFalse(celdaMarine.contiene(marine));
	}
	
	@Test
	public void testSiUnGolliatEstaBajoUnaTormentaPeroSeMueveAntesNoMuere() throws Exception {
		
		this.reiniciarJuego();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionTormenta = new Coordenada(0,20);
		Coordenada ubicacionGolliatBajoTormenta = new Coordenada(5,20);
		Coordenada ubicacionGolliatFueraDeTormenta = new Coordenada(6,20);
		Coordenada ubicacionAltoTemplario = new Coordenada(8,21);
		
		Golliat golliat = new Golliat();
		golliat.moverse(ubicacionGolliatBajoTormenta);
		
		AltoTemplario altoTemplario = new AltoTemplario();
		altoTemplario.moverse(ubicacionAltoTemplario);
		
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorReceptor.asignarUnidad(golliat);
		
		/*
		 * Gana energia el Alto Templario...
		 */
		
		for (int i = 0; i < 5; i++) {
			Juego.getInstance().turnoDe().finalizarTurno();
		}
		
		assertTrue(golliat.vidaActual() == 125);
		altoTemplario.lanzarTormentaPsionica(ubicacionTormenta);
		
		Celda celdaGolliatDentroDeTormenta = Juego.getInstance().getMapa().obtenerCelda(ubicacionGolliatBajoTormenta);
		Celda celdaGolliatFueraDeTormenta = Juego.getInstance().getMapa().obtenerCelda(ubicacionGolliatFueraDeTormenta);
		assertTrue(celdaGolliatDentroDeTormenta.contiene(golliat));
		
		/*
		 * Pasa 1 turno
		 */
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertTrue(celdaGolliatDentroDeTormenta.contiene(golliat));
		
		/*
		 * Movemos al golliat
		 */
		
		golliat.moverse(ubicacionGolliatFueraDeTormenta);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertTrue(celdaGolliatFueraDeTormenta.contiene(golliat));
		
		/*
		 * Y deberia estar el golliat vivo
		 */
		
		Juego.getInstance().turnoDe().finalizarTurno();
		assertTrue(celdaGolliatFueraDeTormenta.contiene(golliat));
		assertTrue(golliat.vidaActual() == 25);
	}
	
	@Test
	public void testSiUnAltoTemplarioUtilizaAlucionacionEnZealotYLaAlucinacionEsAtacadaElTemplarioNoSufreDanios() throws Exception {
		
		this.reiniciarJuego();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarine = new Coordenada(0,20);
		Coordenada ubicacionAltoTemplario = new Coordenada(1,20);
		Coordenada ubicacionZealot = new Coordenada(0,21);
		
		Marine marine = new Marine();
		AltoTemplario altoTemplario = new AltoTemplario();
		Zealot zealot = new Zealot();
		
		marine.moverse(ubicacionMarine);
		altoTemplario.moverse(ubicacionAltoTemplario);
		zealot.moverse(ubicacionZealot);		
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorAtacante.asignarUnidad(zealot);
		jugadorReceptor.asignarUnidad(marine);
		
		altoTemplario.lanzarAlucinacion(zealot); //Se ubicaran cerca de la unidad a alucinar.

	}
	
	@Test
	public void testSiUnaCopiaDelAltoTemplarioMuereElAltoTemplarioYLaOtraCopiaContinuanVivos() throws Exception {
		
		this.reiniciarJuego();
		Mapa mapa = Juego.getInstance().getMapa();
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarine = new Coordenada(0,20);
		Coordenada ubicacionAltoTemplario = new Coordenada(1,20);
		Coordenada ubicacionZealot = new Coordenada(0,21);
		
		Marine marine = new Marine();
		AltoTemplario altoTemplario = new AltoTemplario();
		Zealot zealot = new Zealot();
		
		marine.moverse(ubicacionMarine);
		altoTemplario.moverse(ubicacionAltoTemplario);
		zealot.moverse(ubicacionZealot);		
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorAtacante.asignarUnidad(zealot);
		jugadorReceptor.asignarUnidad(marine);
		
		//El algoritmo busca posiciones disponibles para ubicar a la unidad alucinada
		//En el mapa test, y debido a la ubicacion del zealot, estas posiciones son
		
		Coordenada ubicacionAlucinacion1 = new Coordenada(0,16);
		Coordenada ubicacionAlucinacion2 = new Coordenada(0,17);
		
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion1).colisiona(zealot));
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion2).colisiona(zealot));
		
		altoTemplario.lanzarAlucinacion(zealot);
		
		//assertTrue(mapa.obtenerCelda(ubicacionAlucinacion1).colisiona(zealot));
		//assertTrue(mapa.obtenerCelda(ubicacionAlucinacion2).colisiona(zealot));
		
	}
	
	@Test
	public void testSiElAltoTemplarioMuereSusAlucinacionesMueren() {
		
	}
	
	@Test
	public void testSiUnaAlucinacionDelAltoTemplarioAtacaNoSacaDanio() {
		
	}

}
