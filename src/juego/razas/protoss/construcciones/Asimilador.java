package juego.razas.protoss.construcciones;

import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class Asimilador extends ConstruccionRecolectora {
	
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
	public void recolectar() {

		if (!this.nodoGasVespeno.estaAgotado()) {
			this.propietario.recolectarGasVespeno(this.nodoGasVespeno.extraer());		
		}
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 6);
	}

}
