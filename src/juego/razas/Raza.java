package juego.razas;

import mapa.Coordenada;
import juego.interfaces.commandConstructor.ConstructorCentroDeMineral;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;

public abstract class Raza {

	public void construir(ConstructorCentroDeMineral constructor, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir{}

	public void turnoFinalizado() {}

}