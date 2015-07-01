package ataquesTest;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.excepciones.FueraDeRangoDeAtaque;
import juego.razas.unidades.excepciones.UnidadAliada;
import juego.razas.unidades.excepciones.YaAtacoEnEsteTurno;
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ataqueSimpleTest {

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
	public void testSiUnZealotAtaca5VecesAUnMarineMuere() throws Exception {
		
		this.reiniciarJuego();
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionZealotAtacante = new Coordenada(1,20);
		
		Marine marine = new Marine();
		Zealot zealot = new Zealot();
		
		jugadorAtacante.asignarUnidad(zealot);
		marine.moverse(ubicacionMarineEnemigo);
		jugadorAtacante.finalizarTurno();
		
		jugadorReceptor.asignarUnidad(marine);	
		zealot.moverse(ubicacionZealotAtacante);
		jugadorReceptor.finalizarTurno();
		
		for(int i = 0; i < 5; i++) {
			jugadorAtacante.finalizarTurno();
			jugadorReceptor.finalizarTurno();
			zealot.atacarA(marine);
		}

		assertFalse(mapa.obtenerCelda(ubicacionMarineEnemigo).contiene(marine));
		
	}	
	
	@Test
	public void testSiUnZealotAtacaAUnMarineFueraDeSuRangoDeAtaqueErrorFueraDeRangoDeAtaque() throws Exception {
		
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
		
		exception.expect(FueraDeRangoDeAtaque.class);
		zealot.atacarA(marine);
		
		assertTrue(mapa.obtenerCelda(ubicacionMarineEnemigo).contiene(marine));
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
		
		jugadorTerran.recolectarMinerales(5000);
		jugadorTerran.recolectarGasVespeno(5000);
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
		
		exception.expect(UnidadAliada.class);
		marine1.atacarA(marine2);
		assertTrue(mapa.obtenerCelda(ubicacionValidaMarine2).contiene(marine2));
		
	}	

	@Test
	public void testSiUnaUnidadYaAtacoEnUnTurnoErrorYaAtacoEnEsteTurno() throws Exception {
			
		this.reiniciarJuego();
				
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
		
		zealot.atacarA(marine);
		
		exception.expect(YaAtacoEnEsteTurno.class);
		zealot.atacarA(marine);
		
	}
	
}
