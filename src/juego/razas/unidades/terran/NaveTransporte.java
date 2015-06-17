package juego.razas.unidades.terran;

import juego.bolsas.BolsaDeCostos;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.Terrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.PuertoEstelar;
import juego.razas.unidades.UnidadTransporte;

public class NaveTransporte extends UnidadTransporte implements Terrestre {

	public NaveTransporte() {
		super();
		
		this.rangoDeMovimiento = 1;
		this.vision = 8;
		this.vida = 150;		
		this.bolsaDeCostos = new BolsaDeCostos(100,100,7,2);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
	}
	
	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((PuertoEstelar)construccion).entrenar(this);
	}
	
}
