package juego.razas.unidades.protoss;

import juego.estrategias.MovimientoTerrestre;
import juego.informadores.AtaqueUnidad;
import juego.informadores.Costos;
import juego.razas.unidades.UnidadAtaque;
import juego.decoradores.*;

public class Zealot extends UnidadAtaque {

	public Zealot() {
		
		super();
		this.vida = new Escudo(new Vida(100), 60);		
		this.costos = new Costos(100,0,4,2);
		this.ataqueUnidad = new AtaqueUnidad(8,0,1,0);
		this.pesoTransporte = 2;
		this.estrategiaDeMovimiento = new MovimientoTerrestre(2,7);
		
	}

}
