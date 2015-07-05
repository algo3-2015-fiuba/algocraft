package juego.razas.unidades.terran;

import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.razas.unidades.UnidadTransporte;

public class NaveTransporteTerran extends UnidadTransporte {

	public NaveTransporteTerran() {
		
		super();
		this.vida = new Vida(150);		
		this.costos = new Costos(100,100,7,2);
		this.estrategiaDeMovimiento = new MovimientoTerrestre(5,8);
		
	}
	
}
