package juego.recursos;

import juego.razas.construcciones.ConstruccionRecolectora;

public class Mineral extends Recurso {

	public Mineral(int cantidadInicial) {
		super(cantidadInicial);
	}
	
	@Override
	public boolean puedeRecolectar(ConstruccionRecolectora recolector) {
		return recolector.puedeExtraer(this);
	}
	
}
