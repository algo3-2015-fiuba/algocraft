package juego.razas.terran.construcciones;

import juego.razas.construcciones.ConstruccionMilitar;

public class Barraca extends ConstruccionMilitar {

	public Barraca() {
		super();
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 12);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.vida += 83.33;	
			this.tiempoDeConstruccion++;		
		}	
	}
	
}
