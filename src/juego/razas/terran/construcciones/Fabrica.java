package juego.razas.terran.construcciones;

import juego.razas.construcciones.ConstruccionMilitar;

public class Fabrica extends ConstruccionMilitar {

	public Fabrica() {
		super();
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 12);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.vida += 104.17;	
			this.tiempoDeConstruccion++;		
		}	
	}
	
}
