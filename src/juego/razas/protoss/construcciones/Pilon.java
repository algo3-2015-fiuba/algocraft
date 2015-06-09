package juego.razas.protoss.construcciones;

import juego.razas.construcciones.ConstruccionHabitable;

public class Pilon extends ConstruccionHabitable {

	public Pilon() {
		super();
		this.capacidadDeHabitantes = 5;
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 5);
	}

	@Override
	public void actualizarConstruccion() {
		
		if (!this.construccionFinalizada()) {
			this.vida += 60;
			this.tiempoDeConstruccion++;
		} 
		
	}
	
}
