package juego.razas.terran.construcciones;

import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class Refineria extends ConstruccionRecolectora {
	
	protected GasVespeno nodoGasVespeno;
	
	public Refineria(Recurso recurso) {
		super();
		this.nodoGasVespeno = (GasVespeno) recurso;
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
			this.propietario.recursos().recolectarGasVespeno(extraidos);		
		}
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 6);
	}

}