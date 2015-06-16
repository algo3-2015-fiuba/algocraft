package juego.razas.unidades;

public abstract class UnidadMagica extends Unidad {
	
	protected int energia;
	
	public abstract void regenerarEnergia();
	
	/*
	 * Todas las unidades magicas pierden energia al recibir un
	 * por un EMP
	 */
	@Override
	public void recibirEMP() {
		this.energia = 0;
	}
}
