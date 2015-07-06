package juego.razas.construcciones.terran;

import juego.interfaces.excepciones.UbicacionInvalida;
import juego.jugadores.Jugador;
import juego.mapa.Coordenada;
import juego.razas.construcciones.ConstruccionBase;

public class BaseTerran extends ConstruccionBase {

	public BaseTerran(Jugador propietario, Coordenada posicion)	throws UbicacionInvalida {
		super(propietario, posicion);
	}

}
