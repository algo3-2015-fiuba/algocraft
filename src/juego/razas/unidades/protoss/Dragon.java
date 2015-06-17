package juego.razas.unidades.protoss;

import juego.bolsas.BolsaDeAtaque;
import juego.bolsas.BolsaDeCostos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.protoss.Acceso;
import juego.razas.unidades.UnidadComun;

public class Dragon extends UnidadComun {

	public Dragon() {
		
		super();
		this.rangoDeMovimiento = 1;
		this.vision = 8;
		this.pesoTransporte = 4;
		this.vida = new Escudo(new Vida(100), 80);		
		this.bolsaDeCostos = new BolsaDeCostos(125,50,6,2);
		this.bolsaDeAtaque = new BolsaDeAtaque(20,20,4,4);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
		
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((Acceso)construccion).entrenar(this);
	}
	
}
