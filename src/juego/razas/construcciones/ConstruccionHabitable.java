package juego.razas.construcciones;

import juego.interfaces.Hospedable;

public abstract class ConstruccionHabitable extends Construccion implements Hospedable {

	protected int capacidadDeHabitantes;

	public ConstruccionHabitable() {
		super();
	}
	
	@Override
	public int capacidadDeHabitantes() {
		return this.capacidadDeHabitantes;
	}
	
	@Override
	public boolean puedeHospedarUnidades() { return true; }
	
}
