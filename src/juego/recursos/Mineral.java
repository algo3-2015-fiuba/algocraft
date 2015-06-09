package juego.recursos;

import juego.interfaces.CommandConstructor;

public class Mineral extends Recurso {

	public Mineral(int cantidadInicial) {
		super(cantidadInicial);
	}
	
	@Override
	public boolean esPosibleConstruir(CommandConstructor constructor) { return constructor.puedeExtraer(this); }
	
}
