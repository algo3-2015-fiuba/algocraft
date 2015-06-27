package juego.magias;

import java.util.Collection;
import java.util.Iterator;

import juego.Juego;
import juego.mapa.Celda;
import juego.mapa.Coordenada;
import juego.mapa.Mapa;
import juego.razas.unidades.Unidad;

public class MisilEMP extends Magia {
	
	public MisilEMP() {
		
		super();
		this.costoEnergia = 100;
		
	}

	public void lanzar(Coordenada coordenadaImpacto) {
		
		Mapa mapa = Juego.getInstance().getMapa();
		
		Collection<Celda> celdasEnRango = mapa.obtenerRangoRadialDeCeldas(coordenadaImpacto, 5);
		
		Iterator<Celda> it = celdasEnRango.iterator();
		
		while (it.hasNext()) {
			it.next().afectadaPorMagia(this);
		}			
		
		this.propietario = Juego.getInstance().turnoDe();
		
	}

	@Override
	public void afectar(Unidad unidad) {
		if (!this.propietario.esAliado(unidad)) {
			unidad.afectadaPorMagia(this);		
		}
	}	

	@Override
	public void activar() {
		//Su duracion es de un turno, por lo que se activa una sola vez al lanzarse.
	}
	
	@Override
	public boolean activa() { return false; }
	
}
