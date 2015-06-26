package juego.razas.unidades.terran;

import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.informadores.AtaqueUnidad;
import juego.informadores.Costos;
import juego.razas.unidades.UnidadAtaque;

public class Espectro extends UnidadAtaque {

	public Espectro() {
		
		super();
		this.vida = new Vida(120);		
		this.costos = new Costos(150,100,8,2);
		this.ataqueUnidad = new AtaqueUnidad(8,20,5,5);
		this.estrategiaDeMovimiento = new MovimientoVolador(3,7);
		
	}
	
}
