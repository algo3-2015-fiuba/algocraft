package juego.razas;

import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;

public abstract class Raza {

	public void construir(ConstructorCentroDeMineral constructor, int x, int y) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir{}

	public void turnoFinalizado() {}

}