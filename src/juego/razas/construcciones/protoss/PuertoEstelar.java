package juego.razas.construcciones.protoss;

import java.util.Collection;

import juego.Juego;
import juego.costos.Costos;
import juego.decoradores.Escudo;
import juego.decoradores.Vida;
import juego.estrategias.MovimientoConstruccion;
import juego.interfaces.excepciones.RecursosInsuficientes;
import juego.interfaces.excepciones.SobrePoblacion;
import juego.jugadores.JugadorProtoss;
import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.mapa.excepciones.CoordenadaFueraDeRango;
import juego.razas.construcciones.ConstruccionMilitar;
import juego.razas.unidades.protoss.NaveTransporte;
import juego.razas.unidades.protoss.Scout;

public class PuertoEstelar extends ConstruccionMilitar {
	
	public PuertoEstelar() {
		super();
		this.vida = new Escudo(new Vida(600), 600);
		this.costos = new Costos(150,150,10,0);
		this.estrategiaDeMovimiento = new MovimientoConstruccion(6);
	}

	@Override
	public void actualizarConstruccion() {
		if (!this.construccionFinalizada())	{
			this.costos.disminuirTiempoDeConstruccion();	
			if (this.construccionFinalizada()) {
				((JugadorProtoss)this.propietario).activarArchivoTemplario(true);
			}
		}	
	}

	public void entrenar(NaveTransporte naveTransporte) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(naveTransporte);
		}
	}

	public void entrenar(Scout scout) throws RecursosInsuficientes, SobrePoblacion {
		if (this.propietario == Juego.getInstance().turnoDe()) {
			this.iniciarEntrenamiento(scout);	
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
