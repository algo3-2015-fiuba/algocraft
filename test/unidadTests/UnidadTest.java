package unidadTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import juego.Juego;
import juego.excepciones.ColorInvalido;
import juego.excepciones.FaltanJugadores;
import juego.excepciones.NombreInvalido;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.RequiereAcceso;
import juego.interfaces.excepciones.RequiereBarraca;
import juego.interfaces.excepciones.RequiereFabrica;
import juego.interfaces.excepciones.RequierePuertoEstelar;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.excepciones.MovimientoFueraDeRango;
import juego.mapa.excepciones.NoEstaOcupadoPorUnidad;
import juego.mapa.excepciones.PropietarioInvalido;
import juego.razas.Protoss;
import juego.razas.Terran;
import juego.razas.terran.unidades.Marine;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class UnidadTest {
	
	@Before 
	public void reiniciarJuego() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException {
		
		Juego.getInstance().reiniciar();
		Juego juego = Juego.getInstance(); 
		
		juego.crearJugador("jugadorTerran", new Terran(), Color.red);
		juego.crearJugador("jugadorProtoss", new Protoss(), Color.blue);
		
		juego.iniciarJuego("mapas/test.map");
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testCreoUnaUnidadYSeMueveCorrectamenteDentroDeSuRangoDeTransporte() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica, NoEstaOcupadoPorUnidad, ConstruccionesNoSeMueven, PropietarioInvalido, MovimientoFueraDeRango {
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		
		Jugador jugadorTerran = juego.turnoDe();
		
		Coordenada coordMarine = new Coordenada(2,1);
		
		/*
		 * Por los propositos de la prueba, creamos el Marine programaticamente
		 * También se puede crear con una barraca etc, pero queremos probar solo el movimiento
		 */
		
		Celda celdaMarine = mapa.obtenerCelda(coordMarine);
		Marine marine = new Marine(jugadorTerran);
		marine.ocuparCelda(celdaMarine);
		
		Coordenada coordNueva = new Coordenada(3,1);
		
		marine.moverse(coordNueva);
		
		Celda celdaFinal = mapa.obtenerCelda(coordNueva);
		
		Marine marine2 = (Marine)(celdaFinal.obtenerUnidadTerrestre());
		
		assertTrue(marine.equals(marine2));
	}
	
	@Test
	public void testCreoUnaUnidadNoPuedeMoverseMasLejosQueSuRangoMaximo() throws ColorInvalido, NombreInvalido, FaltanJugadores, IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada, RequiereAcceso, RequierePuertoEstelar, RequiereBarraca, RequiereFabrica, NoEstaOcupadoPorUnidad, ConstruccionesNoSeMueven, PropietarioInvalido, MovimientoFueraDeRango {
		this.reiniciarJuego();
		
		Juego juego = Juego.getInstance();
		Mapa mapa = juego.getMapa();
		
		Jugador jugadorTerran = juego.turnoDe();
		
		Coordenada coordMarine = new Coordenada(2,1);
		
		Celda celdaMarine = mapa.obtenerCelda(coordMarine);
		Marine marine = new Marine(jugadorTerran);
		marine.ocuparCelda(celdaMarine);
		
		/*
		 * Hacemos que el Marine se mueva 5 celdas, mucho mas que su valor de transporte
		 */
		
		Coordenada coordNueva = new Coordenada(7,1); 
		
		exception.expect(CoordenadaFueraDeRango.class);
		marine.moverse(coordNueva);
		
		Celda celdaFinal = mapa.obtenerCelda(coordNueva);
		
		Marine marine2 = (Marine)(celdaFinal.obtenerUnidadTerrestre());
		
		assertTrue(marine.equals(marine2));
	}

}
