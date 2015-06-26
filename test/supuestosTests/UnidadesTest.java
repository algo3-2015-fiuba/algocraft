package supuestosTests;

import static org.junit.Assert.assertTrue;

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
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UnidadesTest {

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
	public void testUnaUnidadNoPuedeCaminarPorUnaZonaDeRecursos() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		Barraca barraca = new Barraca();
		Marine marine = new Marine();
		Coordenada ubicacionValidaBarraca = new Coordenada(0,1);
		Coordenada ubicacionValidaDepositoSuministro = new Coordenada(0,20);
		Coordenada ubicacionPosibleMovimientoMarine = new Coordenada(2,1);
		Coordenada ubicacionConRecursos = new Coordenada(1,0);
		
		jugadorActual.recolectarMinerales(1000);
		jugadorActual.recolectarGasVespeno(1000);
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
		
		barraca.entrenar(marine);
		
		for (int i = 1; i < 5; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
		}
		
		barraca.activarUnidad(marine, ubicacionPosibleMovimientoMarine);
		
		exception.expect(UbicacionInvalida.class);
		marine.moverse(ubicacionConRecursos);
		
	}
	
	@Test
	public void testUnJugadorPierdeVisionDeOtraUnidadSiSeQuedaSinRango() 
			throws RecursosInsuficientes, UbicacionInvalida, RequerimientosInvalidos, SobrePoblacion, NoTieneVision {
		
		this.reiniciarJuego();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(2,20);
		Coordenada ubicacionZealot = new Coordenada(1,20);
		
		
		
		Marine marine = new Marine();
		marine.moverse(ubicacionMarineEnemigo);
		
		Zealot zealot = new Zealot();
		zealot.moverse(ubicacionZealot);
		
		jugadorReceptor.asignarUnidad(marine);	
		jugadorAtacante.asignarUnidad(zealot);
		
		zealot.atacarA(marine); //Puede atacarlo porque tiene vision
		
		/*
		 * Se mueve 12 veces
		 */
		
		for(int i = 0; i < 15; i++) {
			marine.moverse(new Coordenada(3 + i, 20));
		}
		
		exception.expect(NoTieneVision.class);
		zealot.atacarA(marine);
		
	}	
	
}
