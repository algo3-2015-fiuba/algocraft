package juego.recursos;

import mapa.Recurso;

public class GasVespeno extends Recurso {

	public GasVespeno(int cantidadInicial) {
		super(cantidadInicial);
	}
	
	public GasVespeno duplicar() {
		return new GasVespeno(this.cantidad);
	}

}
