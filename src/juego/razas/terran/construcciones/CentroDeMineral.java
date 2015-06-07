package juego.razas.terran.construcciones;

import juego.Juego;
import juego.recursos.Mineral;
import juego.recursos.Recurso;
import juego.recursos.excepciones.RecursoAgotado;
import juego.interfaces.Construible;
import juego.interfaces.Controlable;
import juego.interfaces.Recolector;
import juego.interfaces.excepciones.CeldaOcupada;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.excepciones.CoordenadaFueraDeRango;

public class CentroDeMineral implements Construible, Recolector, Controlable {
	
	private Mineral nodoMineral;
	private int tiempoDeConstruccion;
	private int vida;
	
	public CentroDeMineral(Recurso recurso) {
		super();
		this.nodoMineral = (Mineral) recurso;
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

	@Override
	public void ocuparCelda(Celda celda) throws CeldaOcupada{
		celda.ocuparTierra(this);
	}

	@Override
	public void moverse(Coordenada coordFinal) throws CeldaOcupada, CoordenadaFueraDeRango {
		// TODO Auto-generated method stub
		
	}
	
}
