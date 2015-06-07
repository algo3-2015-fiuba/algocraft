package juego.recursos;

import juego.interfaces.CommandConstructor;


public class GasVespeno extends Recurso {

	public GasVespeno(int cantidadInicial) {
		super(cantidadInicial);
	}
	
	@Override
	public boolean esPosibleConstruir(CommandConstructor constructor) { return constructor.esPosibleExtraer(this); }

}
