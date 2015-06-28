package mapaTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import juego.excepciones.InicioInvalido;
import juego.jugadores.Jugador;
import juego.materiales.Material;
import juego.mapa.Coordenada;
import juego.mapa.GeneradorMapa;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

import org.junit.Test;

public class GeneradorMapaTester {

	@Test
	public void testGeneradorDeMapaObtieneMaterialesCorrectamente() throws InicioInvalido, CoordenadaFueraDeRango {
		
		GeneradorMapa generadorMapa = new GeneradorMapa();
		Collection<Jugador> jugadores = new ArrayList<Jugador>();
		Mapa mapa = generadorMapa.obtenerMapa("mapas/test.map", jugadores);
		
		// El mapa test posee las primeras tres celdas de tierra y la cuarta de aire		
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(0, 0)).getMaterial());
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(1, 0)).getMaterial());
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(2, 0)).getMaterial());
		assertEquals(Material.aire, mapa.obtenerCelda(new Coordenada(3, 0)).getMaterial());
		
	}
	
	@Test
	public void testGeneradorDeMapaObtieneRecursosCorrectamente() throws InicioInvalido, CoordenadaFueraDeRango {
		
		GeneradorMapa generadorMapa = new GeneradorMapa();
		Collection<Jugador> jugadores = new ArrayList<Jugador>();
		Mapa mapa = generadorMapa.obtenerMapa("mapas/test.map", jugadores);
		
		// El mapa test posee en la primera celda un nodo de minerales y en la segunda uno de gas vespeno,
		// en la tercera y cuarta no posee recursos.
		assertTrue(mapa.obtenerCelda(new Coordenada(0, 0)).poseeRecursos());
		assertTrue(mapa.obtenerCelda(new Coordenada(1, 0)).poseeRecursos());
		assertFalse(mapa.obtenerCelda(new Coordenada(2, 0)).poseeRecursos());
		assertFalse(mapa.obtenerCelda(new Coordenada(3, 0)).poseeRecursos());
		
		
	}
	
}
