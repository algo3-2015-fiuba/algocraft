package juego.recursos;

import juego.interfaces.CommandConstrucciones;

public class Mineral extends Recurso {

	public Mineral(int cantidadInicial) {
		super(cantidadInicial);
	}
	
	@Override
	public boolean esPosibleConstruir(CommandConstrucciones constructor) { return constructor.puedeExtraer(this); }
	
}
