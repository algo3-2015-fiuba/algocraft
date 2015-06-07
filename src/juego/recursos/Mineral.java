package juego.recursos;


public class Mineral extends Recurso {

	public Mineral(int cantidadInicial) {
		super(cantidadInicial);
	}
	
	public Mineral duplicar() {
		return new Mineral(this.cantidad);
	}

}
