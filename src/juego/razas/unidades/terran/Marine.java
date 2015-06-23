package juego.razas.unidades.terran;

import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.informacion.Ataques;
import juego.informacion.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.terran.Barraca;
import juego.razas.unidades.UnidadComun;

public class Marine extends UnidadComun {

	public Marine() {
		
		super();
		this.rangoDeMovimiento = 2;
		this.vision = 7;
		this.vida = new Vida(40);		
		this.pesoTransporte = 1;
		this.bolsaDeCostos = new Costos(50,0,3,1);
		this.bolsaDeAtaque = new Ataques(6,6,4,4);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Barraca)construccion).entrenar(this);
	}
	
}
