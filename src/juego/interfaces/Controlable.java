package juego.interfaces;

import juego.interfaces.excepciones.CeldaOcupada;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public interface Controlable {

	public void moverse(Coordenada coordFinal) throws CeldaOcupada, CoordenadaFueraDeRango;
}
