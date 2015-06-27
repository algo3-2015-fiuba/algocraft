package juego.razas.construcciones.terran;

import java.util.Collection;

import juego.Juego;
import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionRecolectora;
import juego.recursos.Mineral;

public class CentroDeMineral extends ConstruccionRecolectora {
	
	public CentroDeMineral() {
		super();
		this.vida = new Vida(500);
		this.costos = new Costos(50,0,4,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(1);
	}
	
	@Override
	public boolean puedeExtraer(Mineral m) { return true; }
	
	@Override
	public void recolectar() {
		if ((this.construccionFinalizada()) && (!this.nodoRecurso.estaAgotado())) {
			int extraidos = this.nodoRecurso.extraer();
			this.propietario.recolectarMinerales(extraidos);		
		}
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 1, 1);
	}
		
}
