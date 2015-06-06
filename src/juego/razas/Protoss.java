package juego.razas;

import mapa.Coordenada;
import juego.interfaces.CommandConstructor;
import juego.interfaces.excepciones.ImposibleConstruir;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;

public class Protoss extends Raza {
	
	@Override
	public void construir(CommandConstructor construccion, Coordenada coordenada) throws RecursosInsuficientes, UbicacionInvalida, ImposibleConstruir{
		construccion.ejecutar(this, coordenada);
	}
}
