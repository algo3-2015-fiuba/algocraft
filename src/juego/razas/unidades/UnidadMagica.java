package juego.razas.unidades;

import juego.energia.Energia;
import juego.magias.MisilEMP;

public abstract class UnidadMagica extends Unidad {
	
	protected Energia energia;
	
	public void afectadaPorMagia(MisilEMP emp) {
		this.energia.deshabilitada();
	}
}
