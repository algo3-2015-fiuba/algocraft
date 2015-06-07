package juego.interfaces;

import juego.interfaces.excepciones.CeldaOcupada;
import juego.mapa.Celda;
import juego.mapa.Coordenada;

public interface Controlable {

	public void ocuparCelda(Celda celda) throws CeldaOcupada;
	public void moverse(Coordenada coordFinal);
	
}
