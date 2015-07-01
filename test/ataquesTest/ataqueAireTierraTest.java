package ataquesTest;

import java.awt.Color;

import juego.Juego;
import juego.excepciones.InicioInvalido;
import juego.jugadores.Jugador;
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.unidades.excepciones.FueraDeRangoDeAtaque;
import juego.razas.unidades.protoss.Zealot;
import juego.razas.unidades.terran.NaveCiencia;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ataqueAireTierraTest {

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
	public void testUnZealotTerrestreNoPuedeAtacarAUnaNaveCienciaVoladora() throws Exception {
		
		this.reiniciarJuego();

		Jugador jugadorReceptor = Juego.getInstance().turnoDe();		
		jugadorReceptor.finalizarTurno();		
		Jugador jugadorAtacante = Juego.getInstance().turnoDe();
		
		Coordenada ubicacionNaveCienciaEnemigo = new Coordenada(0,20);
		Coordenada ubicacionZealotAtacante = new Coordenada(1,20);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.moverse(ubicacionNaveCienciaEnemigo);
		
		Zealot zealot = new Zealot();
		zealot.moverse(ubicacionZealotAtacante);
		
		jugadorReceptor.asignarUnidad(naveCiencia);	
		jugadorAtacante.asignarUnidad(zealot);
		
		exception.expect(FueraDeRangoDeAtaque.class);
		zealot.atacarA(naveCiencia);
		
	}

}
