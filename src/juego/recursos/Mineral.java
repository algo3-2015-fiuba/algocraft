package juego.recursos;

import mapa.Recurso;

public class Mineral extends Recurso {

	public Mineral(int cantidadInicial) {
		super(cantidadInicial);
	}
	
	public Mineral duplicar() {
		return new Mineral(this.cantidad);
	}

}
