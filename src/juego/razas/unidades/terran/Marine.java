package juego.razas.unidades.terran;

import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.informacion.AtaqueUnidad;
import juego.informacion.Costos;
import juego.razas.unidades.UnidadAtaque;

public class Marine extends UnidadAtaque {

	public Marine() {
		
		super();
		this.rangoDeMovimiento = 2;
		this.vision = 7;
		this.vida = new Vida(40);		
		this.pesoTransporte = 1;
		this.costos = new Costos(50,0,3,1);
		this.ataqueUnidad = new AtaqueUnidad(6,6,4,4);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		
	}
	
}
