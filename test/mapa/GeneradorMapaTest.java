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
		
		//Las primeras 5 celdas son tierra
		assertEquals(Materiales.TIERRA, mapa.obtenerCelda(new Coordenada(0, 0)).materialBase());
		assertEquals(Materiales.TIERRA, mapa.obtenerCelda(new Coordenada(1, 0)).materialBase());
		assertEquals(Materiales.TIERRA, mapa.obtenerCelda(new Coordenada(2, 0)).materialBase());
		assertEquals(Materiales.TIERRA, mapa.obtenerCelda(new Coordenada(3, 0)).materialBase());
		assertEquals(Materiales.TIERRA, mapa.obtenerCelda(new Coordenada(4, 0)).materialBase());
		
		//Las primeras 3 celdas son minerales, luego un gas, y luego tierra
		
		assertEquals(mapa.obtenerCelda(new Coordenada(0, 0)).obtenerRecurso().getCantidad(), 500);
		
	}

}
