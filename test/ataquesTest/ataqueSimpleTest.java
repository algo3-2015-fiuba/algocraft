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
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ataqueSimpleTest {

	@Before 
	public void reiniciarJuego() {
		
		Juego.getInstance().reiniciar();
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
	public void testSiUnZealotAtaca5VecesAUnMarineMuere() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion, NoTieneVision {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionZealotAtacante = new Coordenada(1,20);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarineEnemigo);
		
		Zealot zealot = new Zealot();
		zealot.moverse(ubicacionZealotAtacante);
		
		jugadorReceptor.asignarUnidad(marine);	
		jugadorAtacante.asignarUnidad(zealot);
		
		for(int i = 0; i < 5; i++)
			zealot.atacarA(marine);
		
		assertFalse(mapa.obtenerCelda(ubicacionMarineEnemigo).contiene(marine));
		
	}	
	
	@Test
	public void testSiUnZealotAtaca5VecesAUnMarineSinRangoEsteNoMuere() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion, NoTieneVision {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionZealotAtacante = new Coordenada(2,20);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarineEnemigo);
		
		Zealot zealot = new Zealot();
		zealot.moverse(ubicacionZealotAtacante);
		
		jugadorReceptor.asignarUnidad(marine);	
		jugadorAtacante.asignarUnidad(zealot);
		
		for(int i = 0; i < 5; i++)
			zealot.atacarA(marine);
		
		assertTrue(mapa.obtenerCelda(ubicacionMarineEnemigo).contiene(marine));
	}
	
	@Test
	public void testSiUnMismoJugadorCreaDosMarinesEstosNoPuedenAtacarseEntreSiYaQueSonAliados() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion, NoTieneVision {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		Mapa mapa = Juego.getInstance().getMapa();
		
		Barraca barraca = new Barraca();
		Marine marine1 = new Marine();
		Marine marine2 = new Marine();
		
		Coordenada ubicacionValidaMarine1 = new Coordenada(4,20);
		Coordenada ubicacionValidaMarine2 = new Coordenada(3,19);
		Coordenada ubicacionValidaBarraca = new Coordenada(0,20);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(0,1);
		
		jugadorActual.recolectarMinerales(5000);
		jugadorActual.recolectarGasVespeno(5000);
		jugadorActual.construir(barraca, ubicacionValidaBarraca);
		
		for (int i = 1; i < 13; i++) {
			
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			
		}
		
		jugadorActual.construir(new DepositoSuministro(), ubicacionValidaDepositoSuministro);
		
		for (int i = 1; i < 7; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
		}

		barraca.entrenar(marine1);
		barraca.entrenar(marine2);
		
		for (int i = 1; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();;
		}
		
		barraca.activarUnidad(marine1, ubicacionValidaMarine1);
		barraca.activarUnidad(marine2, ubicacionValidaMarine2);
		
		for (int i = 1; i < 7; i++) {
			marine1.atacarA(marine2);
			assertTrue(mapa.obtenerCelda(ubicacionValidaMarine2).contiene(marine2));
		}
		
		// Si el marine1 ataca al marine2 7 veces deberia destruirlo, por lo que deberia desaparecer del mapa.
		// pero como son aliados, permanece en el mapa ya que en realidad no recibio danio alguno.
		marine1.atacarA(marine2);
		assertTrue(mapa.obtenerCelda(ubicacionValidaMarine2).contiene(marine2));
		
	}	

}
