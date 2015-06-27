package juego.razas.unidades.terran;

import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.razas.unidades.Ataques;
import juego.razas.unidades.UnidadAtaque;

public class Espectro extends UnidadAtaque {

	public Espectro() {
		
		super();
		this.vida = new Vida(120);		
		this.costos = new Costos(150,100,8,2);
		this.ataques = new Ataques(8,20,5,5);
		this.estrategiaDeMovimiento = new MovimientoVolador(3,7);
		
	}
	
}
