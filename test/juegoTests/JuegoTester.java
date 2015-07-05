package juegoTests;

import static org.junit.Assert.*;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.BasesInsuficientes;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.InicioInvalido;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.construcciones.terran.DepositoSuministro;
import juego.razas.unidades.excepciones.NoSePuedenAtacarUnidadesAliadas;
import juego.razas.unidades.terran.Marine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JuegoTester {

	@Before 
	public void inicioJuegoCorrectamente() throws InicioInvalido {
		
		Juego.reiniciar();
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testJuegoNoPuedeTenerDosJugadoresConElMismoNombre() throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.black));
		
		exception.expect(NombreInvalido.class);
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));

	}

	@Test
	public void testJuegoNoPuedeTenerDosJugadoresConElMismoColor() throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
		
		exception.expect(ColorInvalido.class);
		juego.crearJugador(new JugadorTerran("jugadorTerran2", Color.red));
	
	}

	@Test
	public void testJuegoNoPuedeEmpezarSinJugadores() throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		exception.expect(FaltanJugadores.class);
		juego.iniciarJuego("mapas/test.map");	
		
	}
	
	@Test 
	public void testJuegoNoPuedeEmpezarConMenosDeDosJugadores()	throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
		
		exception.expect(FaltanJugadores.class);
		juego.iniciarJuego("mapas/test.map");
		
	}

	@Test
	public void testJuegoErrorBasesInsuficientesSiHayMasJugadoresDeLosQuePuedeSoportarElMapa() throws InicioInvalido {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran1", Color.red));
		juego.crearJugador(new JugadorTerran("jugadorTerran2", Color.blue));
		juego.crearJugador(new JugadorTerran("jugadorTerran3", Color.yellow));
		juego.crearJugador(new JugadorTerran("jugadorTerran4", Color.black));
		juego.crearJugador(new JugadorTerran("jugadorTerran5", Color.white));
		juego.crearJugador(new JugadorTerran("jugadorTerran6", Color.green));
		juego.crearJugador(new JugadorTerran("jugadorTerran7", Color.cyan));
		juego.crearJugador(new JugadorTerran("jugadorTerran8", Color.gray));
		juego.crearJugador(new JugadorTerran("jugadorTerran9", Color.magenta));
		
		exception.expect(BasesInsuficientes.class);
		juego.iniciarJuego("mapas/test.map");
		
	}
	
	@Test
	public void testSiElMapaSoportaLaCantidadDeJugadoresNoHayError() throws InicioInvalido {
		
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		
		juego.crearJugador(new JugadorTerran("jugadorTerran1", Color.red));
		juego.crearJugador(new JugadorTerran("jugadorTerran2", Color.blue));
		juego.crearJugador(new JugadorTerran("jugadorTerran3", Color.yellow));
		juego.crearJugador(new JugadorTerran("jugadorTerran4", Color.black));
		
		juego.iniciarJuego("mapas/test.map");

	}
	
	@Test
	public void testSiUnJugadorPierdeSuBaseElJuegoFinalizaYElGanadorEsElOtroJugador() throws InicioInvalido, CoordenadaFueraDeRango {
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		Coordenada coordBaseJugador1 = new Coordenada(5,0);
		Coordenada coordBaseJugador2 = new Coordenada(6,0);	
		Jugador jugador1 = new JugadorTerran("jugadorTerran1", Color.red);
		Jugador jugador2 = new JugadorTerran("jugadorTerran2", Color.blue);
		
		juego.crearJugador(jugador1);
		juego.crearJugador(jugador2);
		juego.iniciarJuego("mapas/small.map");
		Mapa mapa = juego.getMapa();	
		
		Celda celdaBaseJugador1 = mapa.obtenerCelda(coordBaseJugador1);
		Celda celdaBaseJugador2 = mapa.obtenerCelda(coordBaseJugador2);
		Jugador jugadorActual = juego.turnoDe();
		
		for (int i=0; i < 10; i++) {
			jugadorActual.finalizarTurno();
			jugadorActual = juego.turnoDe();
			assertFalse(juego.finalizo());
			assertTrue(celdaBaseJugador1.poseeBase());
			assertTrue(celdaBaseJugador2.poseeBase());
		}
		
		//Elimino las dos bases ya que al ser random no se cual posee el jugador actual, 
		// pero si no es propietario de la base no la eliminara

		jugadorActual.baseDestruida(celdaBaseJugador1.getBase());
		jugadorActual.baseDestruida(celdaBaseJugador2.getBase());
		jugadorActual.finalizarTurno();
		
		assertTrue(juego.finalizo());
		assertTrue(juego.ganador().equals(jugador2));
		
	}
	
	@Test
	public void testSiJugadorTrataDeAtacarSuPropiaBaseErrorUnidadAliada() throws Exception {
		
		
		this.inicioJuegoCorrectamente();
		Juego juego = Juego.getInstance();
		Coordenada coordBaseJugador1 = new Coordenada(5,0);
		Coordenada coordBaseJugador2 = new Coordenada(6,0);
		Coordenada coordMarineJugador1 = new Coordenada(5,1);
		Marine marine = new Marine();
		Jugador jugador1 = new JugadorTerran("jugadorTerran1", Color.red);
		Jugador jugador2 = new JugadorTerran("jugadorTerran2", Color.blue);
		
		juego.crearJugador(jugador1);
		juego.crearJugador(jugador2);
		juego.iniciarJuego("mapas/small.map");
		Mapa mapa = juego.getMapa();	
		
		Celda celdaBase1 = mapa.obtenerCelda(coordBaseJugador1);
		Celda celdaBase2 = mapa.obtenerCelda(coordBaseJugador2);
		jugador1.asignarUnidad(marine);
		marine.moverse(coordMarineJugador1);
		
		assertTrue(jugador1.tieneVision(celdaBase1));
		assertTrue(jugador1.tieneVision(celdaBase2));
		assertTrue(jugador2.tieneVision(celdaBase1));
		assertTrue(jugador2.tieneVision(celdaBase2));
		
		//Al otorgarse random las bases no se cual pertenece a cada jugador
		if (jugador1.esAliado(celdaBase1.getBase())) {
			
			exception.expect(NoSePuedenAtacarUnidadesAliadas.class);
			marine.atacarA(celdaBase1.getBase());
			
		} else {
			
			exception.expect(NoSePuedenAtacarUnidadesAliadas.class);
			marine.atacarA(celdaBase2.getBase());
			
		}
		
	}
	
	@Test
	public void testSiTratoDeUbicarUnaUnidadEntrenadaEnUnaCeldaOcupadaErrorUbicacionInvalida() throws Exception {
		
		this.inicioJuegoCorrectamente();
		JugadorTerran jugador1 = new JugadorTerran("jugadorTerran1", Color.red);
		JugadorTerran jugador2 = new JugadorTerran("jugadorTerran2", Color.blue);
		Juego juego = Juego.getInstance();
		juego.crearJugador(jugador1);
		juego.crearJugador(jugador2);
		juego.iniciarJuego("mapas/small.map");
		Barraca barraca = new Barraca();
		DepositoSuministro depositoSuministro = new DepositoSuministro();
		Marine marine1 = new Marine();
		Marine marine2 = new Marine();
		Coordenada coordenadaBarraca = new Coordenada(0,20);
		Coordenada coordenadaDepositoSuministro = new Coordenada(10,20);
		Coordenada coordenadaValidaMarine = new Coordenada(3,20);
		
		jugador1.recolectarGasVespeno(1000);
		jugador1.recolectarMinerales(1000);
		
		jugador1.construir(depositoSuministro, coordenadaDepositoSuministro);
		
		jugador1.construir(barraca, coordenadaBarraca);

		for (int i = 1; i < 11; i++) {
			jugador1.finalizarTurno();
			jugador2.finalizarTurno();
		}
				
		barraca.entrenar(marine1);
		barraca.entrenar(marine2);
	
		for(int i = 1; i < 5; i++) {
			jugador1.finalizarTurno();
			jugador2.finalizarTurno();
		}
		
		barraca.activarUnidad(marine1, coordenadaValidaMarine);
		
		exception.expect(UbicacionInvalida.class);
		barraca.activarUnidad(marine2, coordenadaValidaMarine);
	
	}
	
}
