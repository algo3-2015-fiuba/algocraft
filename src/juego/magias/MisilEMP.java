package juego.magias;

import java.util.Collection;
import java.util.Iterator;

import juego.energia.Energia;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public class MisilEMP extends Magia {
	
	private int costoEnergia;
	
	public MisilEMP() {
		
		super();
		this.costoEnergia = 100;
		
	}

	public void lanzar(Coordenada coordenadaImpacto) {
		
		Collection<Celda> celdasEnRango = this.obtenerRadioDeImpacto(coordenadaImpacto, 5, 5);
		
		Iterator<Celda> it = celdasEnRango.iterator();
		
		while (it.hasNext()) {
			it.next().afectadaPorMagia(this);
		}			
		
	}

	public boolean esPosibleLanzarlo(Energia energia) {
		
		if (energia.energiaSuficiente(this.costoEnergia)) {
			energia.usar(this.costoEnergia);
			return true;
		}
		
		return false;
	}

	@Override
	public void afectar(Unidad unidad) {
		unidad.afectadaPorMagia(this);		
	}
	
	
	
}
