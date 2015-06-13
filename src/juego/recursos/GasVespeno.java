package juego.recursos;

import juego.razas.construcciones.ConstruccionRecolectora;

public class GasVespeno extends Recurso {

	public GasVespeno(int cantidadInicial) {
		super(cantidadInicial);
	}

	@Override
	public boolean puedeRecolectar(ConstruccionRecolectora recolector) {
		return recolector.puedeExtraer(this);
	}
	
}
