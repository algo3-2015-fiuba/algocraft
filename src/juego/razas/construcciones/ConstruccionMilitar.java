package juego.razas.construcciones;

import juego.interfaces.Militable;

public abstract class ConstruccionMilitar extends Construccion implements Militable {

	public ConstruccionMilitar() {
		super();
	}
	
	@Override
	public boolean puedeCrearUnidades() {
		return true;
	}
		
}
