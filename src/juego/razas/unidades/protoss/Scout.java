package juego.razas.unidades.protoss;

import juego.costos.Costos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.razas.ataques.Ataques;
import juego.razas.unidades.UnidadAtaque;

public class Scout extends UnidadAtaque {

	public Scout() {
		
		super();
		this.vida = new Escudo(new Vida(150), 100);		
		this.costos = new Costos(300,150,9,3);
		this.ataques = new Ataques(8,14,4,4);
		this.estrategiaDeMovimiento = new MovimientoVolador(3,7);
		
	}
	
}
