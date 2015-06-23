package juego.razas.unidades.protoss;

import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.informadores.AtaqueUnidad;
import juego.informadores.Costos;
import juego.razas.unidades.UnidadAtaque;

public class Dragon extends UnidadAtaque {

	public Dragon() {
		
		super();
		this.rangoDeMovimiento = 1;
		this.vision = 8;
		this.pesoTransporte = 4;
		this.vida = new Escudo(new Vida(100), 80);		
		this.costos = new Costos(125,50,6,2);
		this.ataqueUnidad = new AtaqueUnidad(20,20,4,4);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		
	}

}
