package juego.razas.unidades.terran;

import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.informacion.AtaqueUnidad;
import juego.informacion.Costos;
import juego.razas.unidades.UnidadAtaque;

public class Golliat extends UnidadAtaque {
	
	public Golliat() {
		
		super();
		this.rangoDeMovimiento = 1;
		this.vision = 8;
		this.vida = new Vida(125);	
		this.pesoTransporte = 2;
		this.costos = new Costos(100,50,6,2);
		this.ataqueUnidad = new AtaqueUnidad(12,10,6,5);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
	}
	
}
