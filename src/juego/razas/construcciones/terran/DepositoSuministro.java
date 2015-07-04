package juego.razas.construcciones.terran;

import java.util.Collection;

import juego.Juego;
import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionHabitable;

public class DepositoSuministro extends ConstruccionHabitable {

	public DepositoSuministro() {
		super();
		this.vida = new Vida(500);
		this.costos = new Costos(100,0,6,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(2);
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() {
		
		try {
				
			Mapa mapa = Juego.getInstance().getMapa();
			return mapa.obtenerRangoDeCeldas(this.posicion, 2, 1);
		
			
		} catch (CoordenadaFueraDeRango cfdr) {
			
			return null;
			
		}
	
	}
	
}
