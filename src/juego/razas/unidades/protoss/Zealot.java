package juego.razas.unidades.protoss;

import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.unidades.UnidadComun;

public class Zealot extends UnidadComun {

	public Zealot() {
		super();
		
		this.rangoDeMovimiento = 1;
		this.vision = 7;
		this.vida = 100;		
		this.bolsaDeCostos = new BolsaDeCostos(100,0,4,2);
		this.bolsaDeAtaque = new BolsaDeAtaque(8,0,1,0);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Acceso)construccion).entrenar(this);
	}

}
