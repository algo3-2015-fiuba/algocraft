package juego.razas.terran.construcciones;

import juego.razas.construcciones.EdificioRecolector;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;

public class CentroDeMineral extends EdificioRecolector implements Construible, Recolector, Controlable {
	
	protected Mineral nodoMineral;
	protected int tiempoDeConstruccion;
	
	public CentroDeMineral(Recurso recurso) {
		super();
		this.nodoMineral = (Mineral) recurso;
		this.tiempoDeConstruccion = 0;
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
