package mapa;

import static org.junit.Assert.*;

import java.io.IOException;

import mapa.Material.Materiales;
import mapa.excepciones.CoordenadaFueraDeBordes;

import org.junit.Test;

public class GeneradorMapaTest {

	@Test
	public void testCreacionDeCentroMapaAPartirDeArchivoParseaCorrectamente() throws IOException, CoordenadaFueraDeBordes {
		//primero tengo que incializar un mapa
		
		Mapa mapa = new GeneradorMapa("mapas/test.map").crearAPartirDeArchivo();
		
		//Las primeras 3 celdas son minerales, luego un gas, y luego tierra
		assertEquals(Materiales.MINERAL, mapa.obtenerCelda(new Coordenada(0, 0)).getBase());
		assertEquals(Materiales.MINERAL, mapa.obtenerCelda(new Coordenada(1, 0)).getBase());
		assertEquals(Materiales.MINERAL, mapa.obtenerCelda(new Coordenada(2, 0)).getBase());
		assertEquals(Materiales.GAS, mapa.obtenerCelda(new Coordenada(3, 0)).getBase());
		assertEquals(Materiales.TIERRA, mapa.obtenerCelda(new Coordenada(4, 0)).getBase());
		
	}

}
