package juego.razas.terran.construcciones;

import juego.razas.construcciones.EdificioRecolector;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class CentroDeMineral extends EdificioRecolector {
	
	protected Mineral nodoMineral;
	
	public CentroDeMineral(Recurso recurso) {
		super();
		this.nodoMineral = (Mineral) recurso;
	}

	@Override
	public void actualizarConstruccion() { 
		if (!this.construccionFinalizada()) {
			this.vida += 125; 
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
