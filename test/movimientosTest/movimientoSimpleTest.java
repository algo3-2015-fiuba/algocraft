package movimientosTest;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.excepciones.NoSePuedenMoverUnidadesEnemigas;
import juego.razas.unidades.excepciones.NoSePuedenAtacarUnidadesAliadas;
import juego.razas.unidades.excepciones.YaSeMovioEnEsteTurno;
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class movimientoSimpleTest {

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
	public void testSiUnZealotSeMueveDesocupaLaCeldaOcupadaYOcupaUnaNuevaCelda() throws Exception {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
		jugadorTerran.finalizarTurno();
		Jugador jugadorProtoss = Juego.getInstance().turnoDe();

		Coordenada ubicacionViejaZealot = new Coordenada(1,20);
		Coordenada ubicacionNuevaZealot = new Coordenada(1,21);	
		Zealot zealot = new Zealot();
		
		jugadorProtoss.asignarUnidad(zealot);		
		zealot.moverse(ubicacionViejaZealot);

		jugadorProtoss.finalizarTurno();
		jugadorTerran.finalizarTurno();
		
		zealot.moverse(ubicacionNuevaZealot);
		
		assertFalse(mapa.obtenerCelda(ubicacionViejaZealot).contiene(zealot));		
		assertTrue(mapa.obtenerCelda(ubicacionNuevaZealot).contiene(zealot));
		
	}	
	
	@Test
	public void testSiUnZealotSeMueveMuyLejosSaltaExcepcion() throws Exception {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
			
		Juego.getInstance().turnoDe().finalizarTurno();		
		Jugador jugadorZealot = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionViejaZealot = new Coordenada(1,20);
		Coordenada ubicacionNuevaMuylejosZealot = new Coordenada(1,25); //Mas que el rango de un Zealot	
		
		Zealot zealot = new Zealot();
		jugadorZealot.asignarUnidad(zealot);
		
		zealot.moverse(ubicacionViejaZealot);
		
		exception.expect(UbicacionInvalida.class);
		zealot.moverse(ubicacionNuevaMuylejosZealot);
		
		assertFalse(mapa.obtenerCelda(ubicacionViejaZealot).contiene(zealot));
	}
	
	@Test
	public void testSiUnMismoJugadorCreaDosMarinesEstosNoPuedenAtacarseEntreSiYaQueSonAliados() throws Exception {
		
		this.reiniciarJuego();
		Jugador jugadorActual = Juego.getInstance().turnoDe();
		JugadorTerran jugadorTerran = (JugadorTerran)Juego.getInstance().turnoDe();
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
		jugadorTerran.construir(barraca, ubicacionValidaBarraca);
		
		for (int i = 1; i < 13; i++) {
			
			jugadorActual.finalizarTurno();
			jugadorActual = Juego.getInstance().turnoDe();
			
		}
		
		jugadorTerran.construir(new DepositoSuministro(), ubicacionValidaDepositoSuministro);
		
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
		
		exception.expect(NoSePuedenAtacarUnidadesAliadas.class);
		marine1.atacarA(marine2);

		assertTrue(mapa.obtenerCelda(ubicacionValidaMarine2).contiene(marine2));
		
	}	
	
	@Test
	public void testSiUnZealotTrataDeMoverseDosVecesEnUnMismoTurnoErrorYaSeMovioEnEsteTurno() throws Exception {
		
		this.reiniciarJuego();
			
		Juego.getInstance().turnoDe().finalizarTurno();		
		Jugador jugadorZealot = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionViejaZealot = new Coordenada(1,20);
		Coordenada ubicacionNuevaZealot = new Coordenada(2,20); //Mas que el rango de un Zealot	
		
		Zealot zealot = new Zealot();
		jugadorZealot.asignarUnidad(zealot);
		
		zealot.moverse(ubicacionViejaZealot);
		
		exception.expect(YaSeMovioEnEsteTurno.class);
		zealot.moverse(ubicacionNuevaZealot);

	}

	@Test
	public void testJugadorEnemigoNoPuedeMoverUnidadAliada() throws Exception {
		
		this.reiniciarJuego();
		Mapa mapa = Juego.getInstance().getMapa();
		JugadorTerran jugadorTerran = (JugadorTerran) Juego.getInstance().turnoDe();
		jugadorTerran.finalizarTurno();
		JugadorProtoss jugadorProtoss = (JugadorProtoss) Juego.getInstance().turnoDe();
		jugadorProtoss.finalizarTurno();
		
		Marine marine = new Marine();
		Zealot zealot = new Zealot();
		Coordenada ubicacionValidaMarine = new Coordenada(0,20);
		Coordenada nuevaUbicacionMarine = new Coordenada(0,21);
		Coordenada ubicacionValidaZealot = new Coordenada(0,25);
		Coordenada nuevaUbicacionZealot = new Coordenada(0,24);

		jugadorTerran.asignarUnidad(marine);
		marine.moverse(ubicacionValidaMarine);
		jugadorTerran.finalizarTurno();
		
		jugadorProtoss.asignarUnidad(zealot);
		zealot.moverse(ubicacionValidaZealot);
		jugadorProtoss.finalizarTurno();
		
		//No deberia moverse ya que el turno actual es el del jugador terran, que no es aliado del zealot.
		exception.expect(NoSePuedenMoverUnidadesEnemigas.class);
		zealot.moverse(nuevaUbicacionZealot);
		
		assertFalse(mapa.obtenerCelda(nuevaUbicacionZealot).contiene(zealot));
		assertTrue(mapa.obtenerCelda(ubicacionValidaZealot).contiene(zealot));
		
		jugadorTerran.finalizarTurno();
		
		//No deberia moverse ya que el turno actual es el del jugador protoss, que no es aliado del marine.
		exception.expect(NoSePuedenMoverUnidadesEnemigas.class);
		marine.moverse(nuevaUbicacionMarine);
		
		assertFalse(mapa.obtenerCelda(nuevaUbicacionMarine).contiene(marine));
		assertTrue(mapa.obtenerCelda(ubicacionValidaMarine).contiene(marine));
		
	}
	
}
