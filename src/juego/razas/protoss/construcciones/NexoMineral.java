package juego.razas.protoss.construcciones;

import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class NexoMineral extends ConstruccionRecolectora {
	
	protected Mineral nodoMineral;
	
	public NexoMineral(Recurso recurso) {
		super();
		this.nodoMineral = (Mineral) recurso;
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
			this.propietario.recolectarMinerarles(this.nodoMineral.extraer());		
		}
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 4);
	}

	
}