package juego.interfaces.commandConstructor;

import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.razas.Raza;


public class ConstructorCentroDeMineral implements CommandConstructor {

	@Override
	public void construir(Raza raza, int x, int y) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {

		raza.construir(this, x, y);

	}
	
}
