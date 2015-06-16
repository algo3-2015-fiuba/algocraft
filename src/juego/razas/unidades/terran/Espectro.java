package juego.razas.unidades.terran;

import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.estrategias.PosicionVolador;
import juego.interfaces.Volador;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.unidades.UnidadComun;

public class Espectro extends UnidadComun implements Volador {

	public Espectro() {
		super();
		
		this.rangoDeMovimiento = 1;
		this.vision = 8;
		this.vida = 40;		
		this.bolsaDeCostos = new BolsaDeCostos(100,50,6,2);
		this.bolsaDeAtaque = new BolsaDeAtaque(12,10,6,5);
		this.estrategiaDePosicion = new PosicionVolador();
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((PuertoEstelar)construccion).entrenar(this);
	}
	
}
