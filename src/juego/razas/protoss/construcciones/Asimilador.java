package juego.razas.protoss.construcciones;

import juego.razas.construcciones.EdificioRecolector;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class Asimilador extends EdificioRecolector {
	
	protected GasVespeno nodoGasVespeno;
	
	public Asimilador(Recurso recurso) {
		super();
		this.nodoGasVespeno = (GasVespeno) recurso;
	}

	@Override
	public void actualizarConstruccion() { 
		if (!this.construccionFinalizada()) {
			this.vida += 75; 
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
