package juego.razas.unidades;

import juego.magias.MisilEMP;
import juego.transportes.CargaTransporte;

public abstract class UnidadTransporte extends Unidad {

	protected CargaTransporte cargaTransporte;
	
	public void afectadaPorMagia(MisilEMP emp) {
		this.vida.deshabilitar();
	}
	
}
