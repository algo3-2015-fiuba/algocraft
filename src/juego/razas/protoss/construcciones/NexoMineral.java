package juego.razas.protoss.construcciones;

import juego.Juego;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.jugadores.Jugador;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;

public class NexoMineral extends EdificioRecolector implements Construible, Recolector, Controlable {
	
	private Mineral nodoMineral;
	private int tiempoDeConstruccion;
	
	public NexoMineral(Recurso recurso) {
		super();
		this.nodoMineral = (Mineral) recurso;
		this.vida = 0;
		this.tiempoDeConstruccion = 0;
		this.propietario = Juego.getInstance().turnoDe();
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
			Juego.getInstance().turnoDe().recolectarMinerarles(extraidos);		
		}
	}
	
	@Override
	public boolean construccionFinalizada() {
		return (this.tiempoDeConstruccion == 4);
	}

	
}