package juego.razas.unidades.protoss;

import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.protoss.PuertoEstelar;
import juego.razas.unidades.UnidadComun;

public class Scout extends UnidadComun {

	public Scout() {
		
		super();
		this.rangoDeMovimiento = 3;
		this.vision = 7;
		this.vida = new Escudo(new Vida(150), 100);		
		this.bolsaDeCostos = new BolsaDeCostos(300,150,9,3);
		this.bolsaDeAtaque = new BolsaDeAtaque(8,14,4,4);
		this.estrategiaDeMovimiento = new MovimientoVolador();
		
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Acceso)construccion).entrenar(this);
	}
	
}
