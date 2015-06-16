package juego.razas.unidades;

public abstract class UnidadMagica extends Unidad {
	
	protected int energia;
	
	public abstract void regenerarEnergia();
	
	/*
	 * Todas las unidades magicas pierden energia al recibir un
	 * por un EMP
	 */
	@Override
	public void ataqueEMP() {
		this.energia = 0;
	}
}
