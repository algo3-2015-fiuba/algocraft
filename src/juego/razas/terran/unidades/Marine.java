package juego.razas.terran.unidades;

import juego.jugadores.Jugador;
import juego.razas.unidades.Unidad;

public class Marine extends Unidad {

	public Marine(Jugador propietario) {
		super(propietario);
		this.transporte = 3;
		this.esVolador = false;
		this.vision = 7;
		this.ataqueAereo = 6;
		this.ataqueTerrestre = 6;
		this.suministro = 1;
		this.vida = 40;
	}

}
