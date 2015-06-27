package juego.razas.construcciones.terran;

import java.util.Collection;

import juego.Juego;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.informadores.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.jugadores.JugadorTerran;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.terran.Golliat;

public class Fabrica extends ConstruccionMilitar {

	public Fabrica() {
		super();
		this.vida = new Vida(1250);
		this.costos = new Costos(200,100,12,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(6);
	}
	
	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.costos.disminuirTiempoDeConstruccion();
			if (this.construccionFinalizada()) {
				((JugadorTerran)this.propietario).activarPuertoEstelar(true);
			}
		}
	}
	
	public void entrenar(Golliat golliat) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(golliat);
		}
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 2, 3);
	}
	
}
