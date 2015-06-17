package juego.magias;

import java.util.Iterator;

import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public class Radiacion extends Magia {

	public Radiacion() {
		
		super();
		this.costoEnergia = 75;
		
	}

	@Override
	public void lanzar(Coordenada coordImpacto) {
		// La radiacion afecta a una unidad y la unidad irradiada afecta a las demas unidades.
		// Es decir no puede ser lanzada.
	}	

	@Override
	public void afectar(Unidad unidad) {
		
		unidad.afectadaPorMagia(this);
		
	}
	
	public void irradiar(Coordenada ubicacionInfectado) {
		
		Iterator<Celda> it = this.obtenerRadioDeImpacto(ubicacionInfectado, 3, 3).iterator();
		
		while (it.hasNext()) {
			it.next().afectadaPorMagia(this);
		}
		
	}

}
