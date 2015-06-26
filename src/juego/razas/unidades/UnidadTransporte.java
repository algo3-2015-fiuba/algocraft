package juego.razas.unidades;

import juego.transportes.CargaTransporte;

public abstract class UnidadTransporte extends Unidad {

	protected CargaTransporte cargaTransporte;
	
	public UnidadTransporte() {
		
		super();
		this.cargaTransporte = new CargaTransporte(8);
		
	}
	
}
