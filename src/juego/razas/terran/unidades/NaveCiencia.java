package juego.razas.terran.unidades;

import juego.jugadores.Jugador;
import juego.razas.unidades.Unidad;

public class NaveCiencia extends Unidad {

	public NaveCiencia(Jugador propietario) {
		super(propietario);
		this.transporte = 3;
		this.esVolador = true;
		this.vision = 10;
		this.ataqueAereo = 0;
		this.ataqueTerrestre = 0;
		this.suministro = 2;
		this.vida = 200;
	}

}
