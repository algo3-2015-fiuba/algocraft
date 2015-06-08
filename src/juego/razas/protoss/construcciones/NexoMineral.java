package juego.razas.protoss.construcciones;

import juego.razas.construcciones.EdificioRecolector;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class NexoMineral extends EdificioRecolector {
	
	protected Mineral nodoMineral;
	protected int tiempoDeConstruccion;
	
	public NexoMineral(Recurso recurso) {
		super();
		this.nodoMineral = (Mineral) recurso;
		this.tiempoDeConstruccion = 0;
	}

	@Override
	public void actualizarConstruccion() { 
		if (!this.construccionFinalizada()) {
			this.vida += 62.5; 
			this.tiempoDeConstruccion++;
		}
	}
	
	@Override
	public void recolectar() throws RecursoAgotado {

		if (!this.nodoMineral.estaAgotado()) {
			int extraidos = this.nodoMineral.extraer();
			this.propietario.recolectarMinerarles(extraidos);		
		}
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 4);
	}

	
}