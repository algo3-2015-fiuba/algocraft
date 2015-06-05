package juego.razas;

import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;

public class Protoss extends Raza {

	@Override
	public void construir(ConstructorCentroDeMineral constructor, int x, int y) 
			throws ImposibleConstruir { throw new ImposibleConstruir(); }
	
}
