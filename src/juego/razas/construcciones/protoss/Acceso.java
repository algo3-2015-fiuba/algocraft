package juego.razas.construcciones.protoss;

import java.util.Collection;

import juego.Juego;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.protoss.Dragon;
import juego.razas.unidades.protoss.Zealot;
import juego.costos.Costos;
import juego.decoradores.*;
import juego.estrategias.MovimientoConstruccion;

public class Acceso extends ConstruccionMilitar {

	public Acceso() {
		super();
		this.vida = new Escudo(new Vida(500), 500);
		this.costos = new Costos(150,0,8,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(4);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada()) {
			this.costos.disminuirTiempoDeConstruccion();
			if (this.construccionFinalizada()) ((JugadorProtoss)this.propietario).activarPuertoEstelar(true);
		}
		
	}
	
	public void entrenar(Zealot zealot) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(zealot);
		}
	}

	public void entrenar(Dragon dragon) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(dragon);
		}
	}

	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() {
		
		try {
			
			Mapa mapa = Juego.getInstance().getMapa();
			return mapa.obtenerRangoDeCeldas(this.posicion, 2, 2);
			
		} catch (CoordenadaFueraDeRango cfdr) {
			
			return null;
			
		}
		
	}

}
