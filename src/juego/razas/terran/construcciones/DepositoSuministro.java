package juego.razas.terran.construcciones;

import juego.razas.construcciones.ConstruccionHabitable;

public class DepositoSuministro extends ConstruccionHabitable {

	public DepositoSuministro() {
		super();
		this.capacidadDeHabitantes = 5;
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 6);
	}

	@Override
	public void actualizarConstruccion() {
		
		if (!this.construccionFinalizada()) {
			this.vida += 83.33;
			this.tiempoDeConstruccion++;
		} 
		
	}
	
}
