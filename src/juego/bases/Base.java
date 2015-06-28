package juego.bases;

import juego.jugadores.Jugador;

public class Base {

	private Jugador propietario;
	
	public Base(Jugador propietario) {
		this.propietario = propietario;
	}
	
	public Jugador getPropietario() {
		return this.propietario;
	}
	
}
