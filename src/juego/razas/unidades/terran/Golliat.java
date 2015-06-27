package juego.razas.unidades.terran;

import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.razas.unidades.AtaqueUnidad;
import juego.razas.unidades.UnidadAtaque;

public class Golliat extends UnidadAtaque {
	
	public Golliat() {
		
		super();
		this.vida = new Vida(125);	
		this.costos = new Costos(100,50,6,2);
		this.ataqueUnidad = new AtaqueUnidad(12,10,6,5);
		this.pesoTransporte = 2;
		this.estrategiaDeMovimiento = new MovimientoTerrestre(1,8);
	}
	
}
