package juego.razas.unidades.protoss;

import juego.costos.Costos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.razas.unidades.UnidadTransporte;

public class NaveTransporte extends UnidadTransporte {

	public NaveTransporte() {
		
		super();
		this.vida = new Escudo(new Vida(80), 60);		
		this.costos = new Costos(200,0,8,2);
		this.pesoTransporte = 8;
		this.estrategiaDeMovimiento = new MovimientoTerrestre(5,8);
		
	}
	
}
