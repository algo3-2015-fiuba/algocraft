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
		super();
		this.vida = 0;
		this.costoMinerales = 0;
		this.costoGasVespeno = 0;
	}
	
	@Override
	public boolean entrenamientoFinalizado() {
		return (this.tiempoDeConstruccion == 0);
	}
	
}
