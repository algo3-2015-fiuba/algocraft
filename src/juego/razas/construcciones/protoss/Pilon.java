package juego.razas.construcciones.protoss;

import java.util.Collection;

import juego.Juego;
import juego.costos.Costos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionHabitable;

public class Pilon extends ConstruccionHabitable {

	public Pilon() {
		super();
		this.vida = new Escudo(new Vida(300), 300);
		this.costos = new Costos(100,0,5,0);
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
