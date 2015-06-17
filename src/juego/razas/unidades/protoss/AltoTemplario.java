package juego.razas.unidades.protoss;

import juego.bolsas.BolsaDeCostos;
import juego.estrategias.MovimientoTerrestre;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.construcciones.protoss.ArchivoTemplario;
import juego.razas.unidades.UnidadMagica;
import juego.decoradores.*;

public class AltoTemplario extends UnidadMagica {
	
	public static final int ENERGIA_RECUPERADA = 15;
	public static final int RADIO_TORMENTA = 5;

	public AltoTemplario() {
		super();
		
		this.rangoDeMovimiento = 1;
		this.vision = 7;
		this.vida = new Escudo(new Vida(40), 40);		
		this.bolsaDeCostos = new BolsaDeCostos(100,0,4,2);
		this.estrategiaDeMovimiento = new MovimientoTerrestre();
	}

	@Override
	public void entrenador(ConstruccionMilitar construccion) throws RecursosInsuficientes, SobrePoblacion {
		((ArchivoTemplario)construccion).entrenar(this);
	}
	
	@Override
	public void actualizar() {
		this.vida.regenerar();
		if (this.estaInfectado()) { 
			this.infectado.irradiar(this.posicion);
		}
		this.energia.cargar(15);
	}

}
