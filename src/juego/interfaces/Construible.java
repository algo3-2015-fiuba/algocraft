package juego.interfaces;

import juego.jugadores.Jugador;

public interface Construible {

	public boolean construccionFinalizada();
	public void actualizarConstruccion();
	public Jugador getPropietario();
	
}
