package juego.interfaces.commandConstructor;

import mapa.Coordenada;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.razas.Raza;


public class ConstructorCentroDeMineral implements CommandConstructor {

	@Override
	public void construir(Raza raza, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		raza.construir(this, coordenada);
	}
	
}
