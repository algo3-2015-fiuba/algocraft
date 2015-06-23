package juego.razas.unidades.protoss;

import juego.estrategias.MovimientoTerrestre;
import juego.informacion.Ataques;
import juego.informacion.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.unidades.UnidadComun;
import juego.decoradores.*;

public class Zealot extends UnidadComun {

	public Zealot() {
		
		super();
		this.rangoDeMovimiento = 2;
		this.vision = 7;
		this.pesoTransporte = 2;
		this.vida = new Escudo(new Vida(100), 60);		
		this.bolsaDeCostos = new Costos(100,0,4,2);
		this.bolsaDeAtaque = new Ataques(8,0,1,0);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Acceso)construccion).entrenar(this);
	}

}
