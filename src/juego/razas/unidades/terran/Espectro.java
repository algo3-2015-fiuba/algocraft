package juego.razas.unidades.terran;

import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.unidades.UnidadComun;

public class Espectro extends UnidadComun {

	public Espectro() {
		
		super();
		this.rangoDeMovimiento = 3;
		this.vision = 7;
		this.vida = new Vida(120);		
		this.bolsaDeCostos = new BolsaDeCostos(150,100,8,2);
		this.bolsaDeAtaque = new BolsaDeAtaque(8,20,5,5);
		this.estrategiaDeMovimiento = new MovimientoVolador();
		
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((PuertoEstelar)construccion).entrenar(this);
	}
	
}
