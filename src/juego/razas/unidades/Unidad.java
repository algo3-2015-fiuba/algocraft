package juego.razas.unidades;

import juego.interfaces.Atacable;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Entrenable;
import juego.interfaces.Terrestre;
import juego.interfaces.Volador;

public abstract class Unidad implements Controlable, Entrenable, Atacable {
	
	protected int vision;
	protected int costoMinerales, costoGasVespeno;
	protected int suministro;
	protected int vida;
	protected int tiempoDeConstruccion;
	protected boolean esVolador, esTerrestre;
	
	public Unidad() {
		super();
		this.vida = 0;
		this.costoMinerales = 0;
		this.costoGasVespeno = 0;
		this.esVolador = false;
		this.esTerrestre = false;
	}
	
	@Override
	public boolean entrenamientoFinalizado() {
		return (this.tiempoDeConstruccion == 0);
	}
	
	//Por defecto no ocupan el mismo espacio, segun la unidad se especifican sus caracteristicas.
	@Override
	public boolean ocupanMismoEspacio(Terrestre terrestre) { return esTerrestre; }
	
	@Override
	public boolean ocupanMismoEspacio(Volador volador) { return esVolador; }
	
	@Override
	public boolean ocupanMismoEspacio(Construible construible) { return esTerrestre; }
	
}
