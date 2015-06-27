package juego.razas.unidades.protoss;

import juego.costos.Costos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.razas.unidades.AtaqueUnidad;
import juego.razas.unidades.UnidadAtaque;

public class Dragon extends UnidadAtaque {

	public Dragon() {
		
		super();
		this.vida = new Escudo(new Vida(100), 80);		
		this.costos = new Costos(125,50,6,2);
		this.ataqueUnidad = new AtaqueUnidad(20,20,4,4);
		this.pesoTransporte = 4;
		this.estrategiaDeMovimiento = new MovimientoTerrestre(1,8);
		
	}

}
