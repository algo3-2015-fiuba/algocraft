package ataquesTest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;
import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
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
	public void testSiUnMismoJugadorCreaDosMarinesEstosNoPuedenAtacarseEntreSiYaQueSonAliados() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion {
		
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
		
		jugadorActual.bolsaDeRecursos().recolectarMinerales(5000);
		jugadorActual.bolsaDeRecursos().recolectarGasVespeno(5000);
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

		jugadorActual.entrenar(barraca, marine1);
		jugadorActual.entrenar(barraca, marine2);
		
		for (int i = 1; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();;
		}
		
		jugadorActual.activarUnidad(barraca, marine1, ubicacionValidaMarine1);
		jugadorActual.activarUnidad(barraca, marine2, ubicacionValidaMarine2);
		
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