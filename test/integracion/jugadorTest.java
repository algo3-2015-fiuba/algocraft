package integracion;

import static org.junit.Assert.*;

import java.io.IOException;

import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.razas.Terran;
import juego.recursos.Mineral;
import mapa.Coordenada;
import mapa.GeneradorMapa;
import mapa.Mapa;

import org.junit.Test;

public class jugadorTest {

	@Test
	public void testCreacionDeCentroMineral() throws IOException, RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		//primero tengo que incializar un mapa
		
		Mapa mapa = new GeneradorMapa("mapas/test.map").crearAPartirDeArchivo();		
		Terran jugador = new Terran();
		
		Mineral nodoAExtraer = new Mineral(500);
		//construyo un centro de mineral en la posicion donde se que esta el mineral
		ConstructorCentroDeMineral constructorMineral = new ConstructorCentroDeMineral();
		jugador.construir(constructorMineral, new Coordenada(0,0));
		
		//paso turnos, mientras verifico que no recolecta
		
		//paso turnos hasta completar construccion
		
		//verifico que empiezo a sumar pasando turnos.
	}

}
