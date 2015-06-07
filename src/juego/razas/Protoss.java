package juego.razas;

import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.mapa.Coordenada;

public class Protoss extends Raza {
	
	@Override
	public void construir(CommandConstructor construccion, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada{
		construccion.ejecutar(this, coordenada);
	}
	
	@Override
	public void construir(CommandConstructor construccion, Coordenada coordInicial, Coordenada coordFinal) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir, CoordenadaFueraDeRango, CeldaOcupada{
		construccion.ejecutar(this, coordInicial, coordFinal);
	}
}
