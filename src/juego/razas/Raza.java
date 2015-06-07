package juego.razas;

import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.mapa.Coordenada;

public abstract class Raza {

	public abstract void construir(CommandConstructor constructor, Coordenada coordenada) 
			throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir;	

}