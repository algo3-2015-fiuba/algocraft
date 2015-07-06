package juego.razas.construcciones.protoss;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionBase;

public class BaseProtoss extends ConstruccionBase {

	public BaseProtoss(Jugador propietario, Coordenada posicion) throws UbicacionInvalida {
		super(propietario, posicion);	
	}

}
