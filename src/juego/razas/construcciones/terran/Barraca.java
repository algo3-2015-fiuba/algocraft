package juego.razas.construcciones.terran;

import java.util.Collection;

import juego.Juego;
import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.terran.Marine;

public class Barraca extends ConstruccionMilitar {

	public Barraca() {
		super();
		this.vida = new Vida(1000);
		this.costos = new Costos(150,0,12,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(4);
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 2, 2);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.costos.disminuirTiempoDeConstruccion();
			if (this.construccionFinalizada()) {
				((JugadorTerran)this.propietario).activarFabrica(true);
			}
		}
	}
	
	public void entrenar(Marine marine) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(marine);
		}
	}
	
}
