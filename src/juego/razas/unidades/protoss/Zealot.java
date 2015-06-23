package juego.razas.unidades.protoss;

import juego.estrategias.MovimientoTerrestre;
import juego.informacion.AtaquesUnidades;
import juego.informacion.Costos;
import juego.razas.unidades.UnidadAtaque;
import juego.decoradores.*;

public class Zealot extends UnidadAtaque {

	public Zealot() {
		
		super();
		this.rangoDeMovimiento = 2;
		this.vision = 7;
		this.pesoTransporte = 2;
		this.vida = new Escudo(new Vida(100), 60);		
		this.costos = new Costos(100,0,4,2);
		this.bolsaDeAtaque = new AtaquesUnidades(8,0,1,0);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		
	}

}
