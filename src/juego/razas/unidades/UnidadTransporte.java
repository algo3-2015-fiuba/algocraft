package juego.razas.unidades;

import juego.magias.MisilEMP;

public abstract class UnidadTransporte extends Unidad {

	protected int capacidad;
	
	public void afectadaPorMagia(MisilEMP emp) {
		this.vida.deshabilitar();
	}
	
}
