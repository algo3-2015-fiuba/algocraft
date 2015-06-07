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
	public void testCreacionDeCentroMapaAPartirDeArchivoParseaCorrectamente() throws IOException, CoordenadaFueraDeRango {
		//primero tengo que incializar un mapa
		
		GeneradorMapa generadorMapa = new GeneradorMapa();
		Mapa mapa = generadorMapa.obtenerMapa("mapas/test.map");
		
		
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(0, 0)).obtenerMaterial());
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(1, 0)).obtenerMaterial());
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(2, 0)).obtenerMaterial());
		assertEquals(Material.aire, mapa.obtenerCelda(new Coordenada(3, 0)).obtenerMaterial());
		assertEquals(Material.tierra, mapa.obtenerCelda(new Coordenada(4, 0)).obtenerMaterial());
				
		assertFalse(mapa.obtenerCelda(new Coordenada(0, 0)).obtenerRecurso().estaAgotado());
		
	}

}
