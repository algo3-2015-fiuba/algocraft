package juego.razas.terran.construcciones;

import juego.razas.construcciones.EdificioRecolector;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class Refineria extends EdificioRecolector {
	
	protected GasVespeno nodoGasVespeno;
	protected int tiempoDeConstruccion;
	
	public Refineria(Recurso recurso) {
		super();
		this.nodoGasVespeno = (GasVespeno) recurso;
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

		if (!this.nodoGasVespeno.estaAgotado()) {
			int extraidos = this.nodoGasVespeno.extraer();
			this.propietario.recolectarGasVespeno(extraidos);		
		}
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 6);
	}

}