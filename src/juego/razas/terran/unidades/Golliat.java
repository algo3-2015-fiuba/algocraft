package juego.razas.terran.unidades;

import juego.jugadores.Jugador;
import juego.razas.unidades.Unidad;

public class Golliat extends Unidad {

	public Golliat(Jugador propietario) {
		super(propietario);
		this.transporte = 3;
		this.esVolador = false;
		this.vision = 8;
		this.ataqueAereo = 10;
		this.ataqueTerrestre = 12;
		this.suministro = 2;
		this.vida = 125;
	}

}
