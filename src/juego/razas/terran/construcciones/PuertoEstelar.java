package juego.razas.terran.construcciones;

import juego.razas.construcciones.ConstruccionMilitar;

public class PuertoEstelar extends ConstruccionMilitar {

	public PuertoEstelar() {
		super();
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 10);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.vida += 130;	
			this.tiempoDeConstruccion++;		
		}	
	}
	
}
