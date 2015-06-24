package juego.magias;

import java.util.Collection;
import java.util.Iterator;

import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.razas.unidades.Unidad;

public class MisilEMP extends Magia {
	
	public MisilEMP() {
		
		super();
		this.costoEnergia = 100;
		
	}

	public void lanzar(Coordenada coordenadaImpacto) {
		
		Collection<Celda> celdasEnRango = this.obtenerRadioDeImpacto(coordenadaImpacto, 5);
		
		Iterator<Celda> it = celdasEnRango.iterator();
		
		while (it.hasNext()) {
			it.next().afectadaPorMagia(this);
		}			
		
	}

	@Override
	public void afectar(Unidad unidad) {
		unidad.afectadaPorMagia(this);		
	}	
	
	@Override
	public boolean activa() {
		//Su duracion es de un turno
		return false;
	}

	@Override
	public void activar() {
		//Su duracion es de un turno, por lo que se activa una sola vez al lanzarse.
	}
	
}
