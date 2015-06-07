package juego.interfaces;

import juego.interfaces.excepciones.CeldaOcupada;
import juego.interfaces.excepciones.ConstruccionesNoSeMueven;
import juego.jugadores.Jugador;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public interface Controlable {

	public boolean esPropietario(Jugador jugador);
	public void ocuparCelda(Celda celda) throws CeldaOcupada, CoordenadaFueraDeRango;
	public void moverse(Coordenada coordFinal) throws CeldaOcupada, CoordenadaFueraDeRango, ConstruccionesNoSeMueven;
	
}
