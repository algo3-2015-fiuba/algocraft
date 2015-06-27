package juego.razas.unidades.terran;

import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.razas.ataques.Ataques;
import juego.razas.unidades.UnidadAtaque;

public class Marine extends UnidadAtaque {

	public Marine() {
		
		super();
		this.vida = new Vida(40);		
		this.costos = new Costos(50,0,3,1);
		this.ataques = new Ataques(6,6,4,4);
		this.pesoTransporte = 1;
		this.estrategiaDeMovimiento = new MovimientoTerrestre(2,7);
		
	}
	
}
