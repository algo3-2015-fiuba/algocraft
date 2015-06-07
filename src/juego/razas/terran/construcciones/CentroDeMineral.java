package juego.razas.terran.construcciones;

import juego.Juego;
import juego.razas.protoss.construcciones.EdificioRecolector;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.jugadores.Jugador;

public class CentroDeMineral extends EdificioRecolector implements Construible, Recolector, Controlable {
	
	private Mineral nodoMineral;
	private int tiempoDeConstruccion;
	
	public CentroDeMineral(Recurso recurso) {
		super();
		this.nodoMineral = (Mineral) recurso;
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
