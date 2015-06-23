package juego.razas.unidades.terran;

import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.informacion.AtaquesUnidades;
import juego.informacion.Costos;
import juego.razas.unidades.UnidadAtaque;

public class Espectro extends UnidadAtaque {

	public Espectro() {
		
		super();
		this.rangoDeMovimiento = 3;
		this.vision = 7;
		this.vida = new Vida(120);		
		this.costos = new Costos(150,100,8,2);
		this.bolsaDeAtaque = new AtaquesUnidades(8,20,5,5);
		this.estrategiaDeMovimiento = new MovimientoVolador();
		
	}
	
}
