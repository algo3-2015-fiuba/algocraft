package juego.razas;

import mapa.Coordenada;
import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.ImposibleConstruir;

public class Protoss extends Raza {

	@Override
	public void construir(ConstructorCentroDeMineral constructor, Coordenada coordenada) 
			throws ImposibleConstruir { throw new ImposibleConstruir(); }
	
}
