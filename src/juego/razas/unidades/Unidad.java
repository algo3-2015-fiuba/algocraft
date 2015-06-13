package juego.razas.unidades;

import juego.interfaces.Atacable;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;

public abstract class Unidad implements Controlable, Entrenable, Atacable {
	
	protected int vision;
	protected int costoMinerales, costoGasVespeno;
	protected int suministro;
	protected int vida;
	protected int tiempoDeConstruccion;
	
	public Unidad() {
		
	}
	
}
