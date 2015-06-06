package juego.interfaces;

import mapa.Coordenada;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.razas.Protoss;
import juego.razas.Raza;
import juego.razas.Terran;

public abstract class CommandConstructor {

	public void ejecutar(Raza raza, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		raza.construir(this, coordenada);
	}
	
	/* Por default, asumimos que no se puede ejecutar un CommandConstructor en ninguna raza.
	 * Luego hacemos un @Override para las correctas
	 */

	public void ejecutar(Terran raza, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		throw new ImposibleConstruir();
	}
	
	public void ejecutar(Protoss raza, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir {
		throw new ImposibleConstruir();
	}
	
	
	
}
