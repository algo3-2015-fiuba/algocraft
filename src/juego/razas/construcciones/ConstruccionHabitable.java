package juego.razas.construcciones;

public abstract class ConstruccionHabitable extends Construccion {

	protected int capacidadDeHabitantes;

	public ConstruccionHabitable() {
		super();
	}
	
	public int capacidadDeHabitantes() {
		return this.capacidadDeHabitantes;
	}
	
	@Override
	public boolean puedeHospedarUnidades() { return true; }
	
}
