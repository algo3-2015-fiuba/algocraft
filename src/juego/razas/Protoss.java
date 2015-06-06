package juego.razas;

import mapa.Coordenada;
import juego.interfaces.CommandConstructor;
import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.ImposibleConstruir;

public class Protoss extends Raza {

	@Override
	public void construir(CommandConstructor constructor, Coordenada coordenada) 
			throws ImposibleConstruir { throw new ImposibleConstruir(); }
	
}
