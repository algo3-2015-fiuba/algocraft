package juego.interfaces;

import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.razas.Raza;

public interface CommandConstructor {

	public void construir(Raza raza, int x, int y) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir;
	
}
