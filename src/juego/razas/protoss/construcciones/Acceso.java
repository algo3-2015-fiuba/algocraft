package juego.razas.protoss.construcciones;

import juego.razas.construcciones.ConstruccionMilitar;

public class Acceso extends ConstruccionMilitar {

	public Acceso() {
		super();
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 8);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada()) {
			this.vida += 62.5;
			this.tiempoDeConstruccion++;
		}
		
	}

}
