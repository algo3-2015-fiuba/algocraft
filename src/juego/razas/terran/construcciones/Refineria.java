package juego.razas.terran.construcciones;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.jugadores.Jugador;
import juego.razas.protoss.construcciones.EdificioRecolector;
import juego.recursos.GasVespeno;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class Refineria extends EdificioRecolector implements Construible, Recolector, Controlable {
	
	private GasVespeno nodoGasVespeno;
	private int tiempoDeConstruccion;
	
	public Refineria(Recurso recurso) {
		super();
		this.nodoGasVespeno = (GasVespeno) recurso;
		this.vida = 0;
		this.tiempoDeConstruccion = 0;
		this.propietario = Juego.getInstance().turnoDe();
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
			Juego.getInstance().turnoDe().recolectarGasVespeno(extraidos);		
		}
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 6);
	}

}