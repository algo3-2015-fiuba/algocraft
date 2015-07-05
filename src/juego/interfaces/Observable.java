package juego.interfaces;

import juego.jugadores.Jugador;

public interface Observable {

	public void agregarObservador(Controlable controlable);
	public void removerObservador(Controlable controlable);
	public boolean observadaPor(Controlable controlable);
	public boolean observadaPor(Jugador jugador);
}
