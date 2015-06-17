package juego.magias;

import java.util.Iterator;

import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public class Radiacion extends Magia {
	
	private Unidad infectado;
	
	public Radiacion() {
		
		super();
		this.costoEnergia = 75;
		infectado = null;
		
	}

	@Override
	public void activar() {
		this.infectado.afectadaPorMagia(this);
	}
	
	@Override
	public void afectar(Unidad unidad) {
		
		unidad.afectadaPorMagia(this);
		if (infectado == null) infectado = unidad;
		
	}
	
	public void irradiar(Coordenada ubicacionInfectado) {
		
		Iterator<Celda> it = this.obtenerRadioDeImpacto(ubicacionInfectado, 3, 3).iterator();
		
		while (it.hasNext()) {
			it.next().afectadaPorMagia(this);
		}
		
	}

	public void fallecido(Unidad unidad) {
		if (this.infectado == unidad) this.infectado = null;		
	}

}
