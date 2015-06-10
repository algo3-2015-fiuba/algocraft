package juego.razas.terran.unidades;

import juego.jugadores.Jugador;
import juego.razas.unidades.Unidad;

public class Espectro extends Unidad {

	public Espectro(Jugador propietario) {
		super(propietario);
		this.transporte = 3;
		this.esVolador = true;
		this.vision = 7;
		this.ataqueAereo = 20;
		this.ataqueTerrestre = 8;
		this.suministro = 2;
		this.vida = 120;
	}

}
