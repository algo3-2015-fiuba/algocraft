package juego.razas.unidades.terran;

import juego.decoradores.Vida;
import juego.estrategias.MovimientoVolador;
import juego.informacion.Ataques;
import juego.informacion.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.unidades.UnidadAtaque;

public class Espectro extends UnidadAtaque {

	public Espectro() {
		
		super();
		this.rangoDeMovimiento = 3;
		this.vision = 7;
		this.vida = new Vida(120);		
		this.bolsaDeCostos = new Costos(150,100,8,2);
		this.bolsaDeAtaque = new Ataques(8,20,5,5);
		this.estrategiaDeMovimiento = new MovimientoVolador();
		
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((PuertoEstelar)construccion).entrenar(this);
	}
	
}
