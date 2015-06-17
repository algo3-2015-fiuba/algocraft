package juego.razas.unidades.terran;

import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.Fabrica;
import juego.razas.unidades.UnidadComun;

public class Golliat extends UnidadComun {
	
	public Golliat() {
		super();
		
		this.rangoDeMovimiento = 1;
		this.vision = 8;
		this.vida = new Vida(125);		
		this.bolsaDeCostos = new BolsaDeCostos(100,50,6,2);
		this.bolsaDeAtaque = new BolsaDeAtaque(12,10,6,5);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Fabrica)construccion).entrenar(this);
	}
	
}
