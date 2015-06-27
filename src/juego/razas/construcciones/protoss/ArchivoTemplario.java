package juego.razas.construcciones.protoss;

import java.util.Collection;

import juego.Juego;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.informadores.Costos;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.protoss.AltoTemplario;

public class ArchivoTemplario extends ConstruccionMilitar {

	public ArchivoTemplario() {
		super();
		this.vida = new Escudo(new Vida(500), 500);
		this.costos = new Costos(150,200,9,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(6);
	}

	public void entrenar(AltoTemplario altoTemplario) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(altoTemplario);
		}
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() throws CoordenadaFueraDeRango {
		Mapa mapa = Juego.getInstance().getMapa();
		return mapa.obtenerRangoDeCeldas(this.posicion, 2, 3);
	}
	
}
