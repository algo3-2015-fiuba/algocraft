package juego.razas.terran.construcciones;

import juego.razas.construcciones.EdificioAlmacenador;

public class DepositoSuministro extends EdificioAlmacenador {

	public DepositoSuministro() {
		super();
		this.capacidadDePoblacion = 5;
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
