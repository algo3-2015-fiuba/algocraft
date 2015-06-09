package juego.recursos;

import juego.interfaces.CommandConstrucciones;

public class GasVespeno extends Recurso {

	public GasVespeno(int cantidadInicial) {
		super(cantidadInicial);
	}
	
	@Override
	public boolean esPosibleConstruir(CommandConstrucciones constructor) { return constructor.puedeExtraer(this); }
}
