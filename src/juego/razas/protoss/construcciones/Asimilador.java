package juego.razas.protoss.construcciones;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.jugadores.Jugador;
import juego.razas.construcciones.EdificioRecolector;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class Asimilador extends EdificioRecolector implements Construible, Recolector, Controlable {
	
	protected GasVespeno nodoGasVespeno;
	protected int tiempoDeConstruccion;
	
	public Asimilador(Recurso recurso) {
		super();
		this.nodoGasVespeno = (GasVespeno) recurso;
		this.tiempoDeConstruccion = 0;
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
