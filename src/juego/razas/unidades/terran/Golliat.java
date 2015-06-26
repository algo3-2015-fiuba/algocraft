package juego.razas.unidades.terran;

import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.informadores.AtaqueUnidad;
import juego.informadores.Costos;
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
