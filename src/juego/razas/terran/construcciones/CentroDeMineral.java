package juego.razas.terran.construcciones;

import juego.Juego;
import juego.recursos.Mineral;
import juego.recursos.excepciones.RecursoAgotado;
import juego.interfaces.Construible;
import juego.interfaces.Recolector;

public class CentroDeMineral implements Construible, Recolector {
	
	private Mineral nodoMineral;
	private int tiempoDeConstruccion;
	private int vida;
	
	public CentroDeMineral(Mineral nodoAExtraer) {
		super();
		this.nodoMineral = nodoAExtraer;
		this.vida = 0;
		this.tiempoDeConstruccion = 0;
	}

	@Override
	public boolean construccionFinalizada() { return (this.tiempoDeConstruccion == 4); }
	
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
	
}
