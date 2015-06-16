package juego.razas.unidades.terran;

import juego.bolsas.BolsaDeCostos;
import juego.estrategias.PosicionVolador;
import juego.interfaces.Volador;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.unidades.UnidadMagica;

public class NaveCiencia extends UnidadMagica implements Volador {
	

	public NaveCiencia() {
		super();
		
		this.rangoDeMovimiento = 1;
		this.vision = 10;
		this.vida = 200;		
		this.bolsaDeCostos = new BolsaDeCostos(100,225,10,2);
		this.estrategiaDePosicion = new PosicionVolador();
	}
	
	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((PuertoEstelar)construccion).entrenar(this);
	}
	
	public void regenerarEnergia() {
		this.energia += 10;
		
		if(this.energia > 200) this.energia = 200;
	}

}