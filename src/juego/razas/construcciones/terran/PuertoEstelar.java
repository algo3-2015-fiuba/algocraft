package juego.razas.construcciones.terran;

import java.util.Collection;

import juego.Juego;
import juego.costos.Costos;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.terran.Espectro;
import juego.razas.unidades.terran.NaveCiencia;
import juego.razas.unidades.terran.NaveTransporte;

public class PuertoEstelar extends ConstruccionMilitar {

	public PuertoEstelar() {
		super();
		this.vida = new Vida(1300);
		this.costos = new Costos(150,100,10,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(6);
	}

	public void entrenar(Espectro espectro) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(espectro);
		}
	}
	
	public void entrenar(NaveCiencia naveCiencia) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(naveCiencia);
		}
	}

	public void entrenar(NaveTransporte naveTransporte) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(naveTransporte);
		}
	}
	
	@Override
	public Collection<Celda> obtenerRangoDeOcupacion() {
		
		try {
			
			Mapa mapa = Juego.getInstance().getMapa();
			return mapa.obtenerRangoDeCeldas(this.posicion, 2, 3);
		
		} catch (CoordenadaFueraDeRango cfdr) {
		
			return null;
		
		}
	
	}
	
}
