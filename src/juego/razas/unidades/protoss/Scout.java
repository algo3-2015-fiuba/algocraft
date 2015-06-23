package juego.razas.unidades.protoss;

import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.informacion.AtaquesUnidades;
import juego.informacion.Costos;
import juego.razas.unidades.UnidadAtaque;

public class Scout extends UnidadAtaque {

	public Scout() {
		
		super();
		this.rangoDeMovimiento = 3;
		this.vision = 7;
		this.vida = new Escudo(new Vida(150), 100);		
		this.costos = new Costos(300,150,9,3);
		this.bolsaDeAtaque = new AtaquesUnidades(8,14,4,4);
		this.estrategiaDeMovimiento = new MovimientoVolador();
		
	}
	
}
