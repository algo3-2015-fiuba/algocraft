package juego.razas.protoss.construcciones;

import juego.razas.construcciones.ConstruccionMilitar;

public class ArchivosTemplarios extends ConstruccionMilitar {

	public ArchivosTemplarios() {
		super();
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 9);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada()) {
			this.vida += 55.55;
			this.tiempoDeConstruccion++;
		}
		
	}
	
}
