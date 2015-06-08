package mapa;

import static org.junit.Assert.*;

import java.io.IOException;

import juego.materiales.Material;
import juego.mapa.Coordenada;
import juego.mapa.GeneradorMapa;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

import org.junit.Test;

public class GeneradorMapaTest {

	@Test
	public void testGeneradorDeMapaObtieneMaterialesCorrectamente() throws IOException, CoordenadaFueraDeRango {
		
		// Debe ingresarse la ubicacion del mapa que se va a utilizar.
		GeneradorMapa generadorMapa = new GeneradorMapa();
		Mapa mapa = generadorMapa.obtenerMapa("mapas/test.map");
		
		// El mapa test posee las primeras tres celdas de tierra y la cuarta de aire		
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(0, 0)).obtenerMaterial());
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(1, 0)).obtenerMaterial());
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(2, 0)).obtenerMaterial());
		assertEquals(Material.aire, mapa.obtenerCelda(new Coordenada(3, 0)).obtenerMaterial());
		
	}
	
	@Test
	public void testGeneradorDeMapaObtieneRecursosCorrectamente() throws IOException, CoordenadaFueraDeRango {
		
		GeneradorMapa generadorMapa = new GeneradorMapa();
		Mapa mapa = generadorMapa.obtenerMapa("mapas/test.map");
		
		// El mapa test posee en la primera celda un nodo de minerales y en la segunda uno de gas vespeno,
		// en la tercera y cuarta no posee recursos.
		assertTrue(mapa.obtenerCelda(new Coordenada(0, 0)).poseeRecursos());
		assertTrue(mapa.obtenerCelda(new Coordenada(1, 0)).poseeRecursos());
		assertFalse(mapa.obtenerCelda(new Coordenada(2, 0)).poseeRecursos());
		assertFalse(mapa.obtenerCelda(new Coordenada(3, 0)).poseeRecursos());
		
		
	}
	
}
