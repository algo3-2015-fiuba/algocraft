package magiasTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Iterator;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.interfaces.excepciones.EnergiaInsuficiente;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.UnidadAlucinada;
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
		jugadorAtacante.finalizarTurno();
		
		Coordenada ubicacionMarine = new Coordenada(0,20);
		Coordenada ubicacionAltoTemplario = new Coordenada(8,21);
		
		Marine marine = new Marine();
		jugadorReceptor.asignarUnidad(marine);
		marine.moverse(ubicacionMarine);
		jugadorReceptor.finalizarTurno();
	
		AltoTemplario altoTemplario = new AltoTemplario();
		jugadorAtacante.asignarUnidad(altoTemplario);
		altoTemplario.moverse(ubicacionAltoTemplario);

		jugadorAtacante.finalizarTurno();
		jugadorReceptor.finalizarTurno();
		
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
		AltoTemplario altoTemplario = new AltoTemplario();
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		altoTemplario.moverse(ubicacionAltoTemplario);
		jugadorAtacante.finalizarTurno();
		
		jugadorReceptor.asignarUnidad(golliat);
		golliat.moverse(ubicacionGolliatBajoTormenta);
		
		 // Gana energia el Alto Templario...
		
		for (int i = 0; i < 5; i++) {
			Juego.getInstance().turnoDe().finalizarTurno();
		}
		
		assertTrue(golliat.vidaActual() == 125);
		altoTemplario.lanzarTormentaPsionica(ubicacionTormenta);
		
		Celda celdaGolliatDentroDeTormenta = Juego.getInstance().getMapa().obtenerCelda(ubicacionGolliatBajoTormenta);
		Celda celdaGolliatFueraDeTormenta = Juego.getInstance().getMapa().obtenerCelda(ubicacionGolliatFueraDeTormenta);
		
		assertTrue(celdaGolliatDentroDeTormenta.contiene(golliat));
		
		jugadorAtacante.finalizarTurno();
		
		assertTrue(celdaGolliatDentroDeTormenta.contiene(golliat));
		golliat.moverse(ubicacionGolliatFueraDeTormenta);
		
		
		assertTrue(celdaGolliatFueraDeTormenta.contiene(golliat));

		assertTrue(golliat.vidaActual() == 25);
	}
	
	@Test
	public void testSiUnAltoTemplarioUtilizaAlucionacionEnZealotYLaAlucinacionEsAtacadaElTemplarioNoSufreDanios() throws Exception {
		
		this.reiniciarJuego();
		Mapa mapa = Juego.getInstance().getMapa();
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionGolliat = new Coordenada(0,18);		
		Coordenada ubicacionAltoTemplario = new Coordenada(1,20);
		Coordenada ubicacionZealot = new Coordenada(0,21);
		
		Golliat golliat = new Golliat();
		AltoTemplario altoTemplario = new AltoTemplario();
		Zealot zealot = new Zealot();
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorAtacante.asignarUnidad(zealot);
		
		altoTemplario.moverse(ubicacionAltoTemplario);
		zealot.moverse(ubicacionZealot);		
		jugadorAtacante.finalizarTurno();
		
		jugadorReceptor.asignarUnidad(golliat);
		golliat.moverse(ubicacionGolliat);
		jugadorReceptor.finalizarTurno();
		
		//El algoritmo busca posiciones disponibles para ubicar a la unidad alucinada
		//En el mapa test, y debido a la ubicacion del zealot, estas posiciones son
		Coordenada ubicacionAlucinacion1 = new Coordenada(0,19);
		Coordenada ubicacionAlucinacion2 = new Coordenada(0,20);
		
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion1).colisiona(zealot));
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion2).colisiona(zealot));
		
		for (int i=0; i<2; i++) {
			//Paso turnos para que el alto templario cargue energia
			jugadorAtacante.finalizarTurno();
			jugadorReceptor.finalizarTurno();
		}
		
		altoTemplario.lanzarAlucinacion(zealot);
		
		Iterator<Unidad> it = mapa.obtenerCelda(ubicacionAlucinacion2).getUnidades().iterator();
		
		assertTrue(it.hasNext());
		
		UnidadAlucinada alucinacionZealot = (UnidadAlucinada)it.next();
		
		assertTrue(mapa.obtenerCelda(ubicacionAlucinacion2).contiene(alucinacionZealot));
		
		assertTrue(zealot.vidaActual() == 100);
		assertTrue(alucinacionZealot.vidaActual() == 0);
		assertTrue(alucinacionZealot.escudoActual() == 60);
		
		for (int i=0; i< 9; i++) {
			jugadorAtacante.finalizarTurno();
			golliat.atacarA(alucinacionZealot);
			jugadorReceptor.finalizarTurno();

		}
		
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion2).contiene(alucinacionZealot));		
		assertTrue(zealot.vidaActual() == 100);
		assertTrue(alucinacionZealot.escudoActual() == 0);
		
	}
	
	@Test
	public void testSiElAltoTemplarioMuereSusAlucinacionesMueren() throws Exception {
		
		this.reiniciarJuego();
		Mapa mapa = Juego.getInstance().getMapa();
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionGolliat = new Coordenada(0,18);		
		Coordenada ubicacionAltoTemplario = new Coordenada(1,20);
		Coordenada ubicacionZealot = new Coordenada(0,21);
		
		Golliat golliat = new Golliat();
		AltoTemplario altoTemplario = new AltoTemplario();
		Zealot zealot = new Zealot();
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorAtacante.asignarUnidad(zealot);
		
		altoTemplario.moverse(ubicacionAltoTemplario);
		zealot.moverse(ubicacionZealot);		
		jugadorAtacante.finalizarTurno();
		
		jugadorReceptor.asignarUnidad(golliat);
		golliat.moverse(ubicacionGolliat);
		jugadorReceptor.finalizarTurno();
		
		//El algoritmo busca posiciones disponibles para ubicar a la unidad alucinada
		//En el mapa test, y debido a la ubicacion del zealot, estas posiciones son
		Coordenada ubicacionAlucinacion1 = new Coordenada(0,19);
		Coordenada ubicacionAlucinacion2 = new Coordenada(0,20);
		
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion1).colisiona(zealot));
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion2).colisiona(zealot));
		
		for (int i=0; i<2; i++) {
			//Paso turnos para que el alto templario cargue energia
			jugadorAtacante.finalizarTurno();
			jugadorReceptor.finalizarTurno();
		}
		
		altoTemplario.lanzarAlucinacion(zealot);
		
		Iterator<Unidad> it = mapa.obtenerCelda(ubicacionAlucinacion1).getUnidades().iterator();
		
		assertTrue(it.hasNext());
		
		UnidadAlucinada alucinacionZealot1 = (UnidadAlucinada)it.next();
		
		it = mapa.obtenerCelda(ubicacionAlucinacion2).getUnidades().iterator();
		
		assertTrue(it.hasNext());
		
		UnidadAlucinada alucinacionZealot2 = (UnidadAlucinada)it.next();
		
		assertTrue(mapa.obtenerCelda(ubicacionAlucinacion1).contiene(alucinacionZealot1));
		assertTrue(mapa.obtenerCelda(ubicacionAlucinacion2).contiene(alucinacionZealot2));
		
		assertTrue(zealot.vidaActual() == 100);
		assertTrue(alucinacionZealot1.vidaActual() == 0);
		assertTrue(alucinacionZealot1.escudoActual() == 60);
		
		for (int i=0; i< 26; i++) {
			jugadorAtacante.finalizarTurno();
			golliat.atacarA(zealot);
			jugadorReceptor.finalizarTurno();

		}
		
		assertFalse(mapa.obtenerCelda(ubicacionZealot).contiene(zealot));
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion1).contiene(alucinacionZealot1));
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion2).contiene(alucinacionZealot2));
		assertTrue(zealot.vidaActual() == 0);
		assertTrue(alucinacionZealot1.escudoActual() == 60);
		assertTrue(alucinacionZealot2.escudoActual() == 60);
		assertFalse(jugadorReceptor.esAliado(alucinacionZealot1));
		assertFalse(jugadorReceptor.esAliado(alucinacionZealot2));
		assertFalse(jugadorReceptor.esAliado(zealot));
		
	}
	
	@Test
	public void testSiAltoTemplarioQuiereUsarAlucinacionPeroNoPoseeEnergiaSuficienteError() throws Exception {
		
		this.reiniciarJuego();
		Mapa mapa = Juego.getInstance().getMapa();
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();
		
		jugadorReceptor.finalizarTurno();
		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionGolliat = new Coordenada(0,18);		
		Coordenada ubicacionAltoTemplario = new Coordenada(1,20);
		Coordenada ubicacionZealot = new Coordenada(0,21);
		
		Golliat golliat = new Golliat();
		AltoTemplario altoTemplario = new AltoTemplario();
		Zealot zealot = new Zealot();
		
		jugadorAtacante.asignarUnidad(altoTemplario);
		jugadorAtacante.asignarUnidad(zealot);
		
		altoTemplario.moverse(ubicacionAltoTemplario);
		zealot.moverse(ubicacionZealot);		
		jugadorAtacante.finalizarTurno();
		
		jugadorReceptor.asignarUnidad(golliat);
		golliat.moverse(ubicacionGolliat);
		jugadorReceptor.finalizarTurno();
		
		//El algoritmo busca posiciones disponibles para ubicar a la unidad alucinada
		//En el mapa test, y debido a la ubicacion del zealot, estas posiciones son
		Coordenada ubicacionAlucinacion1 = new Coordenada(0,16);
		Coordenada ubicacionAlucinacion2 = new Coordenada(0,17);
		
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion1).colisiona(zealot));
		assertFalse(mapa.obtenerCelda(ubicacionAlucinacion2).colisiona(zealot));
		
		exception.expect(EnergiaInsuficiente.class);
		altoTemplario.lanzarAlucinacion(zealot);
		
	}

}
