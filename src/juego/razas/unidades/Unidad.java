package juego.razas.unidades;

import juego.interfaces.Construible;

public abstract class Unidad {
	
	public Unidad() {
		
	}

	public boolean ocupanMismoEspacio(Construible construible) { return true; }
	
}
