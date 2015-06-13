package juego.razas.unidades;

import juego.interfaces.Atacable;
import juego.interfaces.Atacante;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;

public abstract class Unidad implements Controlable, Atacable, Atacante {
	
	protected boolean esVolador;
	
	public Unidad() {
		
	}

	public boolean ocupanMismoEspacio(Construible construible) {
		return (!this.esVolador);
	}
	
}
