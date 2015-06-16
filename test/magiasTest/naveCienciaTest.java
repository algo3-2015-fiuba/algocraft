package magiasTest;

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
import juego.jugadores.JugadorProtoss;
import juego.jugadores.JugadorTerran;
import juego.mapa.Coordenada;
import juego.razas.factories.UnidadProtossFactory;
import juego.razas.unidades.Unidad;
import juego.razas.unidades.protoss.AltoTemplario;
import juego.razas.unidades.terran.Marine;
import juego.razas.unidades.terran.NaveCiencia;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class naveCienciaTest {

	@Before 
	public void reiniciarJuego() {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		try {
			juego.crearJugador(new JugadorTerran("jugadorTerran", Color.red));
			juego.crearJugador(new JugadorTerran("jugadorTerran2", Color.blue));
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
	public void testSiUnMarineEstaIrradiadoPierdeVida() {
		this.reiniciarJuego();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		marine.ubicar(ubicacionMarineEnemigo);
		
		Juego.getInstance().turnoDe().asignarUnidad(marine);		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.ubicar(ubicacionNaveCiencia);
		
		Juego.getInstance().turnoDe().asignarUnidad(naveCiencia);
		
		naveCiencia.irradiar(marine);
		
		assertEquals(marine.vida(),40);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		assertEquals(marine.vida(),35);
	}
	
	@Test
	public void testSiUnMarineEstaIrradiadoLastimaASusCercanos() {
		this.reiniciarJuego();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionMarineEnemigo2 = new Coordenada(1,20);
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		marine.ubicar(ubicacionMarineEnemigo);
		
		Marine marine2 = new Marine();
		marine2.ubicar(ubicacionMarineEnemigo2);
		
		Juego.getInstance().turnoDe().asignarUnidad(marine);		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.ubicar(ubicacionNaveCiencia);
		
		Juego.getInstance().turnoDe().asignarUnidad(naveCiencia);
		
		/*
		 * Solo irradiamos al marine original
		 */
		
		naveCiencia.irradiar(marine);
		
		/*
		 * Ninguno de los dos pierde vida
		 */
		
		assertEquals(marine.vida(), 40);
		assertEquals(marine2.vida(), 40);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		/*
		 * Al pasar un turno, el primer marine
		 *   - Pierde individualmente 5 de vida,
		 *   - Daña a su compañero por 5
		 */
		
		assertEquals(marine.vida(),35);
		assertEquals(marine2.vida(),35);
	}
	
	@Test
	public void testSiDosMarinesEstanIrradiadosSeLastimanEntreSiMucho() {
		this.reiniciarJuego();
		
		Coordenada ubicacionMarineEnemigo = new Coordenada(0,20);
		Coordenada ubicacionMarineEnemigo2 = new Coordenada(1,20);
		Coordenada ubicacionNaveCiencia = new Coordenada(5,21);
		
		Marine marine = new Marine();
		marine.ubicar(ubicacionMarineEnemigo);
		
		Marine marine2 = new Marine();
		marine2.ubicar(ubicacionMarineEnemigo2);
		
		Juego.getInstance().turnoDe().asignarUnidad(marine);		
		Juego.getInstance().turnoDe().asignarUnidad(marine2);
		Juego.getInstance().turnoDe().finalizarTurno();
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.ubicar(ubicacionNaveCiencia);
		
		Juego.getInstance().turnoDe().asignarUnidad(naveCiencia);
		
		/*
		 * Irradiamos ambos marines
		 */
		
		naveCiencia.irradiar(marine);
		naveCiencia.irradiar(marine2);
		
		/*
		 * Ninguno de los dos pierde vida
		 */
		
		assertEquals(marine.vida(), 40);
		assertEquals(marine2.vida(), 40);
		
		Juego.getInstance().turnoDe().finalizarTurno();
		
		/*
		 * Al pasar un turno, el primer marine
		 *   - Pierde individualmente 5 de vida,
		 *   - Daña a su compañero por 5
		 *   
		 * Luego, el segundo marine repite el proceso
		 * Un daño total de 10 para cada uno
		 */
		
		assertEquals(marine.vida(),30);
		assertEquals(marine2.vida(),30);
	}

}
