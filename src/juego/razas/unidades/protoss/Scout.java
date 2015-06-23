package juego.razas.unidades.protoss;

import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.informacion.Ataques;
import juego.informacion.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.protoss.PuertoEstelar;
import juego.razas.unidades.UnidadAtaque;

public class Scout extends UnidadAtaque {

	public Scout() {
		
		super();
		this.rangoDeMovimiento = 3;
		this.vision = 7;
		this.vida = new Escudo(new Vida(150), 100);		
		this.bolsaDeCostos = new Costos(300,150,9,3);
		this.bolsaDeAtaque = new Ataques(8,14,4,4);
		this.estrategiaDeMovimiento = new MovimientoVolador();
		
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((PuertoEstelar)construccion).entrenar(this);
	}
	
}
