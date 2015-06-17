package unidadesProtossTest;

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
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class escudoProtossTest {

	@Before 
	public void reiniciarJuego() {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {

			juego.crearJugador(new JugadorProtoss("jugadorProtoss", Color.blue));
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
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
	public void testSiUnRecibeDanioSuEscudoSeRegenera() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion, NoTieneVision {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionZealotAtacado = new Coordenada(0,20);
		Coordenada ubicacionMarineAtacante = new Coordenada(1,20);
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarineAtacante);
		
		Zealot zealot = new Zealot();
		zealot.moverse(ubicacionZealotAtacado);
		
		jugadorReceptor.asignarUnidad(zealot);	
		
		jugadorAtacante.asignarUnidad(marine);
		
		//Un zealot tiene 100 de vida y 60 de escudo
		
		//Un marine ataca 6 a tierra
		
		for(int i = 0; i < 20; i++)
			marine.atacarA(zealot);
		
		//Recibio 6x20 = 120 de danio.
		//Recibir 60 de danio mas lo mataria si no regenerara
		
		for(int i = 0; i < 100; i++)
			Juego.getInstance().turnoDe().finalizarTurno();
		
		for(int i = 0; i < 10; i++)
			marine.atacarA(zealot);
		
		assertTrue(mapa.obtenerCelda(ubicacionZealotAtacado).contiene(zealot));
		
	}	
	

}
