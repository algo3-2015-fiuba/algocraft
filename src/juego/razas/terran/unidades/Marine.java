package juego.razas.terran.unidades;

import juego.jugadores.Jugador;
import juego.razas.unidades.Unidad;

public class Marine extends Unidad {

	public Marine(Jugador propietario) {
		super(propietario);
		this.vida = 40;
		this.esVolador = false;
		this.suministro = 1;
		this.transporte = 1;
	}

}
