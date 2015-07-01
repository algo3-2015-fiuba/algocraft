package juego.razas.unidades;

import java.util.Iterator;

import juego.transportes.CargaTransporte;

public abstract class UnidadTransporte extends Unidad {

	protected CargaTransporte cargaTransporte;
	
	public UnidadTransporte() {
		
		super();
		this.cargaTransporte = new CargaTransporte(8);
		
	}
	
	@Override
	protected void morir() {
		
		this.cargaTransporte.perdida();
		this.propietario.fallecido(this);
		this.estrategiaDeMovimiento.desocupar(this);
		Iterator<UnidadAlucinada> it = this.alucinaciones.iterator();
		while (it.hasNext()) {
			it.next().originalMuerto();
		}
		
	}
	
}
